package com.example.mvvmarchapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmarchapp.networkservice.ArticleCalls;
import com.example.mvvmarchapp.models.ArticleResponse;

import static com.example.mvvmarchapp.constants.AppConstants.API_KEY;
//import static com.example.mvvmarchapp.constants.AppConstants.ARTICLE_QUERY;

public class ArticleViewModel extends AndroidViewModel {
    private ArticleCalls articleCalls;
    private LiveData<ArticleResponse> articleResponseLiveData;

    public ArticleViewModel(@NonNull Application application) {
        super(application);

        articleCalls = new ArticleCalls();
        this.articleResponseLiveData = articleCalls.getArticles(API_KEY);
    }

    public LiveData<ArticleResponse> getArticleResponseLiveData(){
        return articleResponseLiveData;
    }
}
