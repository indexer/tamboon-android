package com.indexer.tamboon.rest;

import android.content.Context;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

  private static RestClient instance;
  private final RestService mService;

  private RestClient() {

    String localHostUrl = "http://192.168.43.144:8080";//for test in localhost
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.addInterceptor(
        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE));
    builder.connectTimeout(30, TimeUnit.SECONDS);
    builder.writeTimeout(10, TimeUnit.SECONDS);
    builder.readTimeout(30, TimeUnit.SECONDS);
    OkHttpClient client = builder.build();
    final Retrofit restAdapter =
        new Retrofit.Builder().baseUrl(localHostUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();
    mService = restAdapter.create(RestService.class);
  }

  public static synchronized RestService getService(Context mContext) {
    return getInstance().mService;
  }

  private static RestClient getInstance() {
    if (instance == null) {
      instance = new RestClient();
    }
    return instance;
  }
}
