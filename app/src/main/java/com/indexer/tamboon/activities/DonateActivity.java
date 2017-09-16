package com.indexer.tamboon.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.omise.android.models.Token;
import co.omise.android.ui.CreditCardActivity;
import com.google.gson.JsonObject;
import com.indexer.tamboon.R;
import com.indexer.tamboon.model.Charity;
import com.indexer.tamboon.model.DonateRequest;
import com.indexer.tamboon.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonateActivity extends AppCompatActivity {

  private static final String OMISE_PKEY = "pkey_test_59b6c90mlib17w06kaf";
  private static final int REQUEST_CC = 100;
  @BindView(R.id.amount) TextInputEditText mInputText;
  @BindView(R.id.inputPanel) ConstraintLayout constraintLayout;
  @BindView(R.id.progressPanel) ConstraintLayout progressLayout;
  @BindView(R.id.mProgressBar) ProgressBar mProgress;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_donate);
    ButterKnife.bind(this);
    Charity mCharity = getIntent().getParcelableExtra("item");
  }

  @OnClick(R.id.donate_button) void requestTokenAndDonate() {
    showCreditCardForm();
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
        mDonateRequest.setAmount(Integer.parseInt(mInputText.getText().toString()));
        mDonateRequest.setName(token.card.name);
        constraintLayout.setVisibility(View.GONE);
        progressLayout.setVisibility(View.VISIBLE);

        Call<JsonObject> donateCash = RestClient.getService(this).donateToCharities(mDonateRequest);
        donateCash.enqueue(new Callback<JsonObject>() {
          @Override public void onResponse(@NonNull Call<JsonObject> call,
              @NonNull Response<JsonObject> response) {
            // Log.e("Response", "==" + response.message());
            mProgress.setVisibility(View.GONE);
            constraintLayout.setVisibility(View.VISIBLE);
          }

          @Override public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {

          }
        });

      default:
        super.onActivityResult(requestCode, resultCode, data);
    }
  }
}
