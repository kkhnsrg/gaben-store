package com.kokhan.gabenstore;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CatalogRecyclerViewAdapter extends RecyclerView.Adapter<CatalogRecyclerViewAdapter.SpecialViewHolder> implements Filterable {

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
        holder.tv_product_title.setText(gameList.get(position).getTitle());
        holder.img_product_thumbnail.setImageResource(gameList.get(position).getThumbnail());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            //lambda
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("gamePosition", position);
                bundle.putBoolean("isFromCart", false);

                GameInfoFragment gameInfoFragment = new GameInfoFragment();
                gameInfoFragment.setArguments(bundle);

                ((MainActivity) v.getContext()).getSupportFragmentManager().beginTransaction() //add
                        .replace(R.id.fragment_container, gameInfoFragment).addToBackStack(null)
                        .commit();
            }
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
        protected void publishResults(CharSequence constraint, FilterResults results) {
            gameList.clear();
            gameList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static class SpecialViewHolder extends RecyclerView.ViewHolder {

        TextView tv_product_title;
        ImageView img_product_thumbnail;
        CardView cardView;

        public SpecialViewHolder(View itemView) {
            super(itemView);

            tv_product_title = (TextView) itemView.findViewById(R.id.game_name_id);
            img_product_thumbnail = (ImageView) itemView.findViewById(R.id.game_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);

        }

    }
}
