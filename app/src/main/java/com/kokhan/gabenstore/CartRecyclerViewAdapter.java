package com.kokhan.gabenstore;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.SpecialViewHolder> {

    private Context mContext;
    private List<Game> mData;

    public CartRecyclerViewAdapter(Context mContext, List<Game> mData) {
        this.mContext = mContext;
        this.mData = mData;
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
        Game game = mData.get(position);
        holder.tv_game_name.setText(game.getTitle());
        holder.img_game_thumbnail.setImageResource(game.getThumbnail());
        int finalPrice = game.getCount() * game.getPrice();
        holder.tv_game_price.setText("Final price: ".concat(String.valueOf(finalPrice)).concat("$"));

        holder.viewForeground.setOnClickListener(new View.OnClickListener() {
            //lambda
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("gamePosition", position);
                bundle.putBoolean("isFromCart", true);

                GameInfoFragment gameInfoFragment = new GameInfoFragment();
                gameInfoFragment.setArguments(bundle);

                ((MainActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, gameInfoFragment).addToBackStack(null)
                        .commit();
            }
        });

    }

    public void removeItem(int position){
        Game removedGame = mData.get(position);
        GamesDatasource.getInstance().addExistingGame(removedGame);
        mData.remove(removedGame);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class SpecialViewHolder extends RecyclerView.ViewHolder {

        TextView tv_game_name, tv_game_price;
        ImageView img_game_thumbnail;
        RelativeLayout viewBackground, viewForeground;

        public SpecialViewHolder(View itemView) {
            super(itemView);

            tv_game_name = (TextView) itemView.findViewById(R.id.cart_game_name);
            tv_game_price = (TextView) itemView.findViewById(R.id.cart_game_price);
            img_game_thumbnail = (ImageView) itemView.findViewById(R.id.cart_game_picture);
            viewBackground = (RelativeLayout) itemView.findViewById(R.id.cart_background);
            viewForeground = (RelativeLayout) itemView.findViewById(R.id.cart_view_foreground);
        }


    }
}
