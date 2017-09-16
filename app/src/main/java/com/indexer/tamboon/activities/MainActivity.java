package com.indexer.tamboon.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.omise.android.models.Token;
import co.omise.android.ui.CreditCardActivity;
import com.google.gson.JsonObject;
import com.indexer.tamboon.R;
import com.indexer.tamboon.adapter.ListAdapter;
import com.indexer.tamboon.adapter.SpacesItemDecoration;
import com.indexer.tamboon.model.DonateRequest;
import com.indexer.tamboon.rest.RestClient;
import com.indexer.tamboon.viewmodel.CharitiesListViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  CharitiesListViewModel viewModel;
  @BindView(R.id.charities_list) RecyclerView mRecyclerView;
  @BindView(R.id.mProgressBar) ProgressBar mProgress;
  ListAdapter mListAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    mListAdapter = new ListAdapter();
    mProgress.setVisibility(View.VISIBLE);
    viewModel = ViewModelProviders.of(this).get(CharitiesListViewModel.class);
    viewModel.getCharitiest()
        .observe(this, mCharitiesList -> {
              if (mCharitiesList != null) {
                mProgress.setVisibility(View.GONE);
                mListAdapter.setItems(mCharitiesList);
                mRecyclerView.setVisibility(View.VISIBLE);
              }
            }
        );

    mRecyclerView.setAdapter(mListAdapter);
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
        LinearLayoutManager.VERTICAL, false));
    SpacesItemDecoration dividerItemDecoration =
        new SpacesItemDecoration(16);
    mRecyclerView.addItemDecoration(dividerItemDecoration);

    //  showCreditCardForm();
  }


}
