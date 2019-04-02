package com.kokhan.gabenstore.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.kokhan.gabenstore.data.DataStorage;
import com.kokhan.gabenstore.data.Game;
import com.kokhan.gabenstore.R;
import com.kokhan.gabenstore.activity.AppActivity;
import com.kokhan.gabenstore.adapter.CartRecyclerItemTouchHelper;
import com.kokhan.gabenstore.adapter.CartRecyclerViewAdapter;
import com.kokhan.gabenstore.adapter.RecyclerItemTouchHelperListener;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements RecyclerItemTouchHelperListener {


    private List<Game> gameList;
    private CartRecyclerViewAdapter adapter;
    private AppActivity activity;
    private RecyclerView recyclerView;
    private TextView tvEmptyCart;

    public CartFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (AppActivity) getActivity();
        gameList = DataStorage.getInstance().getCartGameList();
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.cart_recyclerview_id);
        tvEmptyCart = (TextView) view.findViewById(R.id.empty_cart_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL));
        adapter = new CartRecyclerViewAdapter(gameList);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack =
                new CartRecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);

        cartEntityCheck();
        return view;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof CartRecyclerViewAdapter.SpecialViewHolder) {
            int deleteIndex = viewHolder.getAdapterPosition();
            adapter.removeItem(deleteIndex);
            cartEntityCheck();
            AHBottomNavigation navigation = (AHBottomNavigation) activity.findViewById(R.id.bottom_navigation);
            navigation.setNotification(DataStorage.getInstance().getCartGameList().size(), 1);
        }
    }

    private void cartEntityCheck() {
        if (gameList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            tvEmptyCart.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tvEmptyCart.setVisibility(View.GONE);
        }
    }
}
