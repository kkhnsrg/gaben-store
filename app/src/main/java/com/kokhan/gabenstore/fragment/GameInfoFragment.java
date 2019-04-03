package com.kokhan.gabenstore.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.kokhan.gabenstore.data.DataStorage;
import com.kokhan.gabenstore.data.Game;
import com.kokhan.gabenstore.R;
import com.kokhan.gabenstore.activity.AppActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameInfoFragment extends Fragment {

    private AppActivity activity;
    private TextView tvTitle, tvDescription, tvPrice, tvCount;
    private ImageView imageView;
    private NumberPicker numberPicker;
    private AppCompatButton btnBuy;

    private static final String PRICE_CONSTANT = "Price: ";
    private static final String COUNT_CONSTANT = "Count: ";
    private static final String HAVENT_GAME_MESSAGE = "We haven't this game. Sorry :c";

    public GameInfoFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (AppActivity) getActivity();
        assert getArguments() != null;
        int gamePosition = getArguments().getInt("gamePosition");
        boolean isFromCart = getArguments().getBoolean("isFromCart");
        View view = inflater.inflate(R.layout.fragment_gameinfo, container, false);

        tvTitle = view.findViewById(R.id.game_title);
        tvDescription = view.findViewById(R.id.game_description);
        tvPrice = view.findViewById(R.id.game_price);
        tvCount = view.findViewById(R.id.game_count);
        imageView = view.findViewById(R.id.game_thumbnail);
        btnBuy = view.findViewById(R.id.buy_button);
        numberPicker = view.findViewById(R.id.game_number_picker);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        final Game currentGame;
        if (isFromCart) {
            btnBuy.setVisibility(View.GONE);
            numberPicker.setVisibility(View.GONE);
            //TODO fix bug with delete
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
                    builder.setMessage(HAVENT_GAME_MESSAGE);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    int addedGameCount = numberPicker.getValue();
                    Game addedGame = new Game(currentGame.getTitle(), currentGame.getDescription(),
                            currentGame.getThumbnail(), addedGameCount, currentGame.getPrice());
                    DataStorage.getInstance().getCartGameList().add(addedGame);
                    currentGame.setCount(currentGame.getCount() - addedGameCount);
                    tvCount.setText(COUNT_CONSTANT.concat(String.valueOf(currentGame.getCount())));
                    activity.setNotificationBadgeValue();
                    setNumberPickerValue(currentGame.getCount());
                }
            }
        };
        btnBuy.setOnClickListener(onClickListenerBuyBtn);

        tvTitle.setText(title);
        tvDescription.setText(description);
        tvCount.setText(COUNT_CONSTANT.concat(String.valueOf(count)));
        tvPrice.setText(PRICE_CONSTANT.concat(String.valueOf(price).concat("$")));
        imageView.setImageResource(image);
        numberPicker.setMinValue(1);
        setNumberPickerValue(currentGame.getCount());
        return view;
    }

    private void setNumberPickerValue(int gameCount) {
        numberPicker.setMaxValue(gameCount == 0 ? 1 : gameCount);
    }
}
