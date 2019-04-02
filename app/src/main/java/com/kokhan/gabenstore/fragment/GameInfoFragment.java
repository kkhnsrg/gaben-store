package com.kokhan.gabenstore;


import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameInfoFragment extends Fragment {

    private TextView tvtitle, tvdescription, tvprice, tvcount;
    private ImageView img;
    private NumberPicker numberPicker;
    private AppCompatButton btnbuy;

//    CartRecyclerViewAdapter adapter;

    private static final String PRICE_CONSTANT = "Price: ";
    private static final String COUNT_CONSTANT = "Count: ";

    public GameInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final MainActivity activity = (MainActivity) getActivity();
//        new CartRecyclerViewAdapter(getActivity(), DataStorage.getInstance().getCartGameList());
        int gamePosition = getArguments().getInt("gamePosition");
        boolean isFromCart = getArguments().getBoolean("isFromCart");
        View view = inflater.inflate(R.layout.fragment_gameinfo, container, false);

        tvtitle = (TextView) view.findViewById(R.id.product_title);
        tvdescription = (TextView) view.findViewById(R.id.product_description);
        tvprice = (TextView) view.findViewById(R.id.product_price);
        tvcount = (TextView) view.findViewById(R.id.product_count);
        img = (ImageView) view.findViewById(R.id.productthumbnail);
        btnbuy = (AppCompatButton) view.findViewById(R.id.buy_button);
        numberPicker = (NumberPicker) view.findViewById(R.id.game_number_picker);
        //numberPicker.setKeyboardNavigationCluster(false);

        final Game currentGame;
        if (isFromCart) {
            btnbuy.setVisibility(View.GONE);
            numberPicker.setVisibility(View.GONE);
            currentGame = DataStorage.getInstance().getCartGameList().get(gamePosition);
        } else {
            currentGame = DataStorage.getInstance().getCatalogGameList().get(gamePosition);
        }

        String title = currentGame.getTitle();
        String description = currentGame.getDescription();
        int price = currentGame.getPrice();
        int count = currentGame.getCount();
        int image = currentGame.getThumbnail();

        View.OnClickListener onClickListenerBuyBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentGame.getCount() <= 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage("We haven't this game. Sorry :c");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    int addedGameCount = numberPicker.getValue();
                    Game addedGame = new Game(currentGame.getTitle(), currentGame.getDescription(),
                            currentGame.getThumbnail(), addedGameCount, currentGame.getPrice());
                    DataStorage.getInstance().getCartGameList().add(addedGame);
                    currentGame.setCount(currentGame.getCount() - addedGameCount);
                    tvcount.setText(COUNT_CONSTANT.concat(String.valueOf(currentGame.getCount())));
                    AHBottomNavigation navigation = (AHBottomNavigation) activity.findViewById(R.id.bottom_navigation);
                    navigation.setNotification(DataStorage.getInstance().getCartGameList().size(), 1);
                    setNumberPickerValue(currentGame.getCount());
                    //adapter.notifyDataSetChanged();
                }
            }
        };
        btnbuy.setOnClickListener(onClickListenerBuyBtn);


        tvtitle.setText(title);
        tvdescription.setText(description);
        tvcount.setText(COUNT_CONSTANT.concat(String.valueOf(count)));
        tvprice.setText(PRICE_CONSTANT.concat(String.valueOf(price).concat("$")));
        img.setImageResource(image);
        numberPicker.setMinValue(1);
        setNumberPickerValue(currentGame.getCount());
        return view;
    }

    private void setNumberPickerValue(int gameCount) {
        if (gameCount == 0) {
            numberPicker.setMaxValue(1);
        } else {
            numberPicker.setMaxValue(gameCount);
        }
    }
}
