package com.example.mvvmarchapp.networkservice;

import com.example.mvvmarchapp.models.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {

    @GET("/v2/top-headlines?sources=bbc-news&apiKey=d775b57a33d1471b8d31893ecf1d3079")
    Call<ArticleResponse> getArticles(
            @Query("apikey") String apiKey
    );

}
