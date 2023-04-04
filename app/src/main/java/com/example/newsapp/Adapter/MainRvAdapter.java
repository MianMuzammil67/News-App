package com.example.newsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapp.Activities.DetailActivity;
import com.example.newsapp.Model.NewsHeadlines;
import com.example.newsapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainRvAdapter extends RecyclerView.Adapter<MainRvAdapter.ViewHolder> {
    Context context;
    ArrayList<NewsHeadlines>list;

    public MainRvAdapter(Context context, ArrayList<NewsHeadlines> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MainRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_rv_view,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MainRvAdapter.ViewHolder holder, int position) {
        holder.txt_headline.setText(list.get(position).getTitle());
        holder.txt_source.setText(list.get(position).getSource().getName());
        Glide.with(context).
                load(list.get(position).getUrlToImage()).
                centerCrop().
                placeholder(R.drawable.placeholder).
                into(holder.img_Headline);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("MAIN_IMAGE",list.get(position).getUrlToImage());
                intent.putExtra("TITLE",list.get(position).getTitle());
                intent.putExtra("AUTHOR",list.get(position).getAuthor());
                intent.putExtra("DESCRIPTION",list.get(position).getDescription());
                intent.putExtra("PUBLISH_AT",list.get(position).getPublishedAt());
                intent.putExtra("CONTENT",list.get(position).getContent());
                intent.putExtra("URL",list.get(position).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_headline,txt_source;
        ImageView img_Headline;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_headline=itemView.findViewById(R.id.headLine);
            txt_source = itemView.findViewById(R.id.textSource);
            img_Headline = itemView.findViewById(R.id.imgHeadline);


        }
    }
}
