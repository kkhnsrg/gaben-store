package com.kokhan.gabenstore.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.kokhan.gabenstore.data.Game;
import com.kokhan.gabenstore.fragment.GameInfoFragment;
import com.kokhan.gabenstore.R;
import com.kokhan.gabenstore.activity.AppActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CatalogRecyclerViewAdapter extends RecyclerView.Adapter<CatalogRecyclerViewAdapter.SpecialViewHolder> implements Filterable {

    private static String GAME_POSITION = "gamePosition";
    private static String IS_FROM_CART = "isFromCart";

    private Context mContext;

    private List<Game> gameList;
    private List<Game> gameListFull;

    public CatalogRecyclerViewAdapter(Context mContext, List<Game> gameList) {
        this.mContext = mContext;
        this.gameList = gameList;
        gameListFull = new ArrayList<>(gameList);
    }

    @NonNull
    @Override
    public SpecialViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.cardview_item_catalog, viewGroup, false);
        return new SpecialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SpecialViewHolder holder, final int position) {
        holder.tvGameTitle.setText(gameList.get(position).getTitle());
        holder.imgGameThumbnail.setImageResource(gameList.get(position).getThumbnail());

        holder.cardView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt(GAME_POSITION, position);
            bundle.putBoolean(IS_FROM_CART, false);

            GameInfoFragment gameInfoFragment = new GameInfoFragment();
            gameInfoFragment.setArguments(bundle);

            ((AppActivity) v.getContext()).getSupportFragmentManager().beginTransaction() //add
                    .replace(R.id.fragment_container, gameInfoFragment).addToBackStack(null)
                    .commit();
        });

    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    @Override
    public Filter getFilter() {
        return gameFilter;
    }

    private Filter gameFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Game> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(gameListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Game game : gameListFull) {
                    if(game.getTitle().toLowerCase().contains(filterPattern)) { //mb startwith
                        filteredList.add(game);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void publishResults(CharSequence constraint, FilterResults results) {
            gameList.clear();
            gameList.addAll((List<Game>)results.values);
            notifyDataSetChanged();
        }
    };

    public static class SpecialViewHolder extends RecyclerView.ViewHolder {

        TextView tvGameTitle;
        ImageView imgGameThumbnail;
        CardView cardView;

        public SpecialViewHolder(View itemView) {
            super(itemView);
            tvGameTitle = itemView.findViewById(R.id.game_name_id);
            imgGameThumbnail = itemView.findViewById(R.id.game_img_id);
            cardView = itemView.findViewById(R.id.cardview_id);
        }
    }
}
