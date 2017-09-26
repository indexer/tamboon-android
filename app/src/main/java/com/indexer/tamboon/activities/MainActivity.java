package com.indexer.tamboon.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.indexer.tamboon.R;
import com.indexer.tamboon.adapter.ListAdapter;
import com.indexer.tamboon.adapter.SpacesItemDecoration;
import com.indexer.tamboon.viewmodel.CharitiesListViewModel;

public class MainActivity extends AppCompatActivity {

  CharitiesListViewModel viewModel;
  @BindView(R.id.charities_list) RecyclerView mRecyclerView;
  @BindView(R.id.mProgressBar) ProgressBar mProgress;
  @BindView(R.id.mError) TextView mErrorInfo;
  @BindView(R.id.mToolbar) android.support.v7.widget.Toolbar mToolBar;
  @BindString(R.string.no_data) String no_data;
  ListAdapter mListAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    mListAdapter = new ListAdapter();
    mProgress.setVisibility(View.VISIBLE);
    setSupportActionBar(mToolBar);
    viewModel = ViewModelProviders.of(this).get(CharitiesListViewModel.class);
    viewModel.getCharities().observe(this, mCharitiesList -> {
      if (mCharitiesList != null) {
        mProgress.setVisibility(View.GONE);
        mListAdapter.setItems(mCharitiesList);
        mRecyclerView.setVisibility(View.VISIBLE);
      } else {
        mErrorInfo.setText(no_data);
        mErrorInfo.setVisibility(View.VISIBLE);
        mProgress.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
      }
    });

    mRecyclerView.setAdapter(mListAdapter);
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    SpacesItemDecoration dividerItemDecoration = new SpacesItemDecoration(16);
    mRecyclerView.addItemDecoration(dividerItemDecoration);
    //  showCreditCardForm();
  }
}
