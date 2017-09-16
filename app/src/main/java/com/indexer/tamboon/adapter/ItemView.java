package com.indexer.tamboon.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.indexer.tamboon.R;
import com.indexer.tamboon.base.BaseViewHolder;
import com.indexer.tamboon.model.Charity;
import com.squareup.picasso.Picasso;

public class ItemView extends BaseViewHolder {
  @BindView(R.id.item_image) ImageView itemImage;
  @BindView(R.id.item_name) TextView mItemName;

  public ItemView(View itemView, OnItemClickListener listener) {
    super(itemView, listener);
    ButterKnife.bind(this, itemView);
  }

  public void onBind(Charity product, int position) {
    Picasso.with(itemView.getContext())
        .load(product.getLogoUrl())
        .placeholder(R.drawable.ic_launcher_background)
        .into(itemImage);
    mItemName.setText(product.getName());
  }
}
