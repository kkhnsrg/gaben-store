package com.kokhan.gabenstore;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements RecyclerItemTouchHelperListener {


    List<Game> gameList;
    CartRecyclerViewAdapter adapter;
    MainActivity activity;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        gameList = CartEntity.getInstance().getGameList();
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.cart_recyclerview_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL));
        adapter = new CartRecyclerViewAdapter(this.getContext(), gameList);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack =
                new CartRecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);


        return view;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if(viewHolder instanceof CartRecyclerViewAdapter.SpecialViewHolder){
            int deleteIndex = viewHolder.getAdapterPosition();
            adapter.removeItem(deleteIndex);

            AHBottomNavigation navigation = (AHBottomNavigation) activity.findViewById(R.id.bottom_navigation);
            navigation.setNotification(CartEntity.getInstance().getGameList().size(), 1);
        }
    }
}
