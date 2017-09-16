package com.indexer.tamboon.rest;

import com.google.gson.JsonObject;
import com.indexer.tamboon.model.Charity;
import com.indexer.tamboon.model.DonateRequest;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestService {
  @GET("/")
  Call<List<Charity>> getCharities();

  @POST("/donate")
  Call<JsonObject> donateToCharities(@Body DonateRequest donateRequest);
}
