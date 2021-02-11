package com.example.mvvmarchapp.networkservice;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmarchapp.models.ArticleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleCalls {
    private static final String TAG = ArticleCalls.class.getSimpleName();
    private ApiRequest apiRequest;

    public ArticleCalls(){
        apiRequest = RetrofitBuild.getRetrofitInstance().create(ApiRequest.class);
    }


    public LiveData<ArticleResponse> getArticles(String key){
        final MutableLiveData<ArticleResponse> data = new MutableLiveData<>();
        apiRequest.getArticles(key)
                .enqueue(new Callback<ArticleResponse>() {
                    @Override
                    public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                        Log.d(TAG, "onResponse response = " + response);

                        if(response.body() != null){
                            data.setValue(response.body());

                            Log.d(TAG, "articles total results= " + response.body().getTotalResults());
                            Log.d(TAG, "articles size= " + response.body().getArticles().size());
                            Log.d(TAG, "articles status= " + response.body().getStatus());
                            Log.d(TAG, "articles title position at 0= " + response.body().getArticles().get(0).getTitle());

                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
