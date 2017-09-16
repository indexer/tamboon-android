package com.indexer.tamboon.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.indexer.tamboon.R;
import com.indexer.tamboon.activities.DonateActivity;
import com.indexer.tamboon.base.BaseViewHolder;
import com.indexer.tamboon.model.Charity;
import com.squareup.picasso.Picasso;

public class ItemView extends BaseViewHolder {
  @BindView(R.id.item_image) ImageView itemImage;
  @BindView(R.id.item_name) TextView mItemName;
  private Charity mCharity;

  public ItemView(View itemView, OnItemClickListener listener) {
    super(itemView, listener);
    ButterKnife.bind(this, itemView);
  }

  @Override public void onClick(View v) {
    Intent intent = new Intent(itemView.getContext(), DonateActivity.class);
    intent.putExtra("item", mCharity);
    itemView.getContext().startActivity(intent);
  }

  public void onBind(Charity charity, int position) {
    mCharity = charity;
    Picasso.with(itemView.getContext())
        .load(charity.getLogoUrl())
        .placeholder(R.drawable.ic_launcher_background)
        .into(itemImage);
    mItemName.setText(charity.getName());
  }
}
