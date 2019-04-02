package com.kokhan.gabenstore;

import androidx.recyclerview.widget.RecyclerView;

interface RecyclerItemTouchHelperListener {
    void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
}
