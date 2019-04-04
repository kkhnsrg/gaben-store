package com.kokhan.gabenstore.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kokhan.gabenstore.data.DataStorage;
import com.kokhan.gabenstore.data.Game;
import com.kokhan.gabenstore.R;
import com.kokhan.gabenstore.adapter.CatalogRecyclerViewAdapter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CatalogFragment extends Fragment {

    private CatalogRecyclerViewAdapter adapter;

    public CatalogFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        List<Game> gameList = DataStorage.getInstance().getCatalogGameList();
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.catalog_recyclerview_id);
        adapter = new CatalogRecyclerViewAdapter(this.getContext(), gameList);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        recyclerView.setAdapter(adapter);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}
