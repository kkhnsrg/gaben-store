package com.kokhan.gabenstore.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kokhan.gabenstore.data.DataStorage;
import com.kokhan.gabenstore.data.Game;
import com.kokhan.gabenstore.fragment.GameInfoFragment;
import com.kokhan.gabenstore.R;
import com.kokhan.gabenstore.activity.AppActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.SpecialViewHolder> {

    private static String FINAL_PRICE = "Final price: ";
    private static String GAME_POSITION = "gamePosition";
    private static String IS_FROM_CART = "isFromCart";

    private List<Game> cartGamesList;

    public CartRecyclerViewAdapter(List<Game> cartGamesList) {
        this.cartGamesList = cartGamesList;
    }

    @NonNull
    @Override
    public SpecialViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.card_list_item_cart, viewGroup, false);
        return new SpecialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SpecialViewHolder holder, final int position) {
        Game game = cartGamesList.get(position);
        holder.tvGameName.setText(game.getTitle());
        holder.imgGameThumbnail.setImageResource(game.getThumbnail());
        int finalPrice = game.getCount() * game.getPrice();
        holder.tvGamePrice.setText(FINAL_PRICE.concat(String.valueOf(finalPrice)).concat("$"));

        holder.viewForeground.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt(GAME_POSITION, position);
            bundle.putBoolean(IS_FROM_CART, true);

            GameInfoFragment gameInfoFragment = new GameInfoFragment();
            gameInfoFragment.setArguments(bundle);

            ((AppActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, gameInfoFragment).addToBackStack(null)
                    .commit();
        });
    }

    public void removeItem(int position) {
        Game removedGame = cartGamesList.get(position);
        addExistingGameToCatalog(removedGame);
        cartGamesList.remove(removedGame);
        notifyItemRemoved(position);
    }

    private void addExistingGameToCatalog(Game game) {
        for (Game currentGame : DataStorage.getInstance().getCatalogGameList()) {
            if (currentGame.getTitle().equals(game.getTitle())) {
                currentGame.setCount(currentGame.getCount() + game.getCount());
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return cartGamesList.size();
    }

    public static class SpecialViewHolder extends RecyclerView.ViewHolder {

        TextView tvGameName, tvGamePrice;
        ImageView imgGameThumbnail;
        RelativeLayout viewBackground, viewForeground;

        public SpecialViewHolder(View itemView) {
            super(itemView);

            tvGameName = itemView.findViewById(R.id.cart_game_name);
            tvGamePrice = itemView.findViewById(R.id.cart_game_price);
            imgGameThumbnail = itemView.findViewById(R.id.cart_game_picture);
            viewBackground = itemView.findViewById(R.id.cart_background);
            viewForeground = itemView.findViewById(R.id.cart_view_foreground);
        }
    }
}
