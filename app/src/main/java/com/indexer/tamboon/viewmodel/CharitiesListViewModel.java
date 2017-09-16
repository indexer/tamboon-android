package com.indexer.tamboon.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import com.indexer.tamboon.model.Charity;
import com.indexer.tamboon.rest.RestClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharitiesListViewModel extends AndroidViewModel {

  private MutableLiveData<List<Charity>> mCharitiestList;

  public LiveData<List<Charity>> getCharitiest() {
    mCharitiestList = new MutableLiveData<>();
    fetchData();
    return mCharitiestList;
  }

  public CharitiesListViewModel(Application application) {
    super(application);
  }

  private void fetchData() {
    Call<List<Charity>> productReturnObjectCall =
        RestClient.getService(this.getApplication().getApplicationContext())
            .getCharities();
    productReturnObjectCall.enqueue(new Callback<List<Charity>>() {

      @Override public void onResponse(@NonNull Call<List<Charity>> call,
          @NonNull Response<List<Charity>> response) {
        if (response.isSuccessful()) {
          mCharitiestList.setValue(response.body());
        }
      }

      @Override public void onFailure(@NonNull Call<List<Charity>> call, @NonNull Throwable t) {

      }
    });
  }
}
