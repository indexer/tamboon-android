package com.indexer.tamboon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import co.omise.android.models.Token;
import co.omise.android.ui.CreditCardActivity;

public class MainActivity extends AppCompatActivity {
  private static final String OMISE_PKEY = "pkey_test_59b6c90mlib17w06kaf";
  private static final int REQUEST_CC = 100;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
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
        Log.e("Token", "current" + token.id);

      default:
        super.onActivityResult(requestCode, resultCode, data);
    }
  }
}
