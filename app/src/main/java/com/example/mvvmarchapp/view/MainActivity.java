package com.example.mvvmarchapp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.mvvmarchapp.R;
import com.example.mvvmarchapp.adapters.ArticleAdapter;
import com.example.mvvmarchapp.models.Article;
import com.example.mvvmarchapp.viewmodel.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
//    private ViewPager viewPager;
    private ProgressBar progressBar;
    private LinearLayoutManager linearLayoutManager;
    private ArticleAdapter adapter;
    private ArrayList<Article> articleArrayList = new ArrayList<>();
    private ArticleViewModel articleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_circular_article);
        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new ArticleAdapter(this, articleArrayList);
        recyclerView.setAdapter(adapter);
        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
        getArticles();
    }

    private void getArticles(){
        articleViewModel.getArticleResponseLiveData().observe(this, articleResponse -> {
            if(articleResponse != null){
                progressBar.setVisibility(View.GONE);
                List<Article> articles = articleResponse.getArticles();
                articleArrayList.addAll(articles);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
