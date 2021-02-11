package com.example.mvvmarchapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvmarchapp.R;
import com.example.mvvmarchapp.models.Article;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private Context context;
    public ArrayList<Article> articleArrayList;

    public ArticleAdapter(Context context, ArrayList<Article> article){
        this.context = context;
        this.articleArrayList = article;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_each_set, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = articleArrayList.get(position);
        holder.title.setText(article.getTitle());
        holder.authorAndPublishedAt.setText("-" + article.getAuthor() + "|" + "Published At: " + article.getPublishedAt());
        holder.description.setText(article.getDescription());
        holder.content.setText(article.getContent());
        Glide.with(context)
                .load(article.getUrlToImage())
                .into(holder.imgViewCover);
    }

    @Override
    public int getItemCount() {
        System.out.println("size here in the holder is: " + articleArrayList.size());
        return articleArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imgViewCover;
        private final TextView title;
        private final TextView authorAndPublishedAt;
        private final TextView description;
        private final TextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgViewCover = itemView.findViewById(R.id.imgViewCover);
            title = itemView.findViewById(R.id.articleTitle);
            authorAndPublishedAt = itemView.findViewById(R.id.articleAuthorAndPublishedAt);
            description = itemView.findViewById(R.id.articleDescription);
            content = itemView.findViewById(R.id.articleContentView);
        }
    }
}
