package com.indexer.tamboon;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.omise.android.models.Token;
import co.omise.android.ui.CreditCardActivity;
import com.google.gson.JsonObject;
import com.indexer.tamboon.adapter.ListAdapter;
import com.indexer.tamboon.adapter.SpacesItemDecoration;
import com.indexer.tamboon.model.Charity;
import com.indexer.tamboon.model.DonateRequest;
import com.indexer.tamboon.rest.RestClient;
import com.indexer.tamboon.viewmodel.CharitiesListViewModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
  private static final String OMISE_PKEY = "pkey_test_59b6c90mlib17w06kaf";
  private static final int REQUEST_CC = 100;
  CharitiesListViewModel viewModel;
  @BindView(R.id.charities_list) RecyclerView mRecyclerView;
  ListAdapter mListAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    mListAdapter = new ListAdapter();
    viewModel = ViewModelProviders.of(this).get(CharitiesListViewModel.class);
    viewModel.getCharitiest().observe(this, mCharitiesList -> {
      mListAdapter.setItems(mCharitiesList);
    });

    mRecyclerView.setAdapter(mListAdapter);
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
        LinearLayoutManager.VERTICAL, false));
    SpacesItemDecoration dividerItemDecoration =
        new SpacesItemDecoration(16);
    mRecyclerView.addItemDecoration(dividerItemDecoration);

    //  showCreditCardForm();
  }

  private void showCreditCardForm() {
    Intent intent = new Intent(this, CreditCardActivity.class);
    intent.putExtra(CreditCardActivity.EXTRA_PKEY, OMISE_PKEY);
    startActivityForResult(intent, REQUEST_CC);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
      case REQUEST_CC:
        if (resultCode == CreditCardActivity.RESULT_CANCEL) {
          return;
        }

        Token token = data.getParcelableExtra(CreditCardActivity.EXTRA_TOKEN_OBJECT);
        // process your token here.
        DonateRequest mDonateRequest = new DonateRequest();
        mDonateRequest.setToken(token.id);
        mDonateRequest.setAmount(10000);
        mDonateRequest.setName(token.card.name);

        Call<JsonObject> donateCash = RestClient.getService(this).donateToCharities(mDonateRequest);
        donateCash.enqueue(new Callback<JsonObject>() {
          @Override public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            Log.e("Response", "==" + response.message());
          }

          @Override public void onFailure(Call<JsonObject> call, Throwable t) {

          }
        });

      default:
        super.onActivityResult(requestCode, resultCode, data);
    }
  }
}
