package com.example.newsapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.newsapp.Model.NewsHeadlines;
import com.example.newsapp.R;
import com.example.newsapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
//        NewsHeadlines headlines = new NewsHeadlines();

        String image = getIntent().getStringExtra("MAIN_IMAGE");
        String tittle = getIntent().getStringExtra("TITLE");
        String date = getIntent().getStringExtra("PUBLISH_AT");
        String description = getIntent().getStringExtra("DESCRIPTION");
        String content = getIntent().getStringExtra("CONTENT");
        String author = getIntent().getStringExtra("AUTHOR");
        String url = getIntent().getStringExtra("URL");

        binding.tvheadline.setText(tittle);
        binding.tvDate.setText(date);
        binding.tvAuthor.setText(author);
        binding.tvDescription.setText(description);
        binding.tvcontent.setText(content);
        binding.fullArticle.setOnClickListener(view -> {

            goToUrl(url);
        });



        Glide.with(this)
                .load(image)
                .fitCenter().placeholder(R.drawable.placeholder)
                .into(binding.detailImage);

    }
    public void goToUrl(String url){
        Uri uri= Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}