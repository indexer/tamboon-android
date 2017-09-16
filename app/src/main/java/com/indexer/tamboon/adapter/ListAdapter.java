package com.indexer.tamboon.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.indexer.tamboon.R;
import com.indexer.tamboon.base.BaseAdapter;
import com.indexer.tamboon.model.Charity;

public class ListAdapter extends BaseAdapter<ItemView, Charity> {
  @Override public ItemView onCreateViewHolder(ViewGroup parent, int
      viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
    return new ItemView(view, null);
  }

  @Override public int getItemCount() {
    return super.getItemCount();
  }

  @Override public void onBindViewHolder(ItemView holder, int position) {
    holder.onBind(mItems.get(position), position);
  }
}
