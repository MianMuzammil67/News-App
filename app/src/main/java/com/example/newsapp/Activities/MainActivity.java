package com.example.newsapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.newsapp.Adapter.MainRvAdapter;
import com.example.newsapp.Model.NewsHeadlines;
import com.example.newsapp.Model.Source;
import com.example.newsapp.R;
import com.example.newsapp.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    MainRvAdapter adapter;
    ArrayList<NewsHeadlines> newsList;
    String url = "https://saurav.tech/NewsAPI/top-headlines/category/general/us.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        newsList = new ArrayList<>();

        getNews(url);

        binding.entertainment.setOnClickListener(this);
        binding.health.setOnClickListener(this);
        binding.technology.setOnClickListener(this);
        binding.science.setOnClickListener(this);
        binding.sports.setOnClickListener(this);
        binding.general.setOnClickListener(this);
        binding.business.setOnClickListener(this);

//        binding.general.setOnClickListener(view -> {
//            binding.generall.setBackgroundResource(R.drawable.circuler_slect_bg);
//            binding.sportss.setBackgroundResource(R.drawable.circuler_bg);
//
//
//        });
//        binding.sports.setOnClickListener(view -> {
//            binding.sportss.setBackgroundResource(R.drawable.circuler_slect_bg);
//            binding.generall.setBackgroundResource(R.drawable.circuler_bg);
//
//
//        });


        adapter = new MainRvAdapter(this, newsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.mainRecyclerView.setLayoutManager(layoutManager);
        binding.mainRecyclerView.setAdapter(adapter);

//        RequestManager manager = new RequestManager(MainActivity.this);
//        manager.getHeadlines(listener,"general",null);
    }
    //    private final onFetchDataListener<ApiResponse> listener = new onFetchDataListener() {
//        @Override
//        public void onFetchData(List<ApiResponse> list, String massage) {
//
//        }
//
//        @Override
//        public void onError(String massage) {
//
//        }
//    }
    void getNews(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Log.e("response", response);
                try {
                    JSONObject mainObject = new JSONObject(response);

                    if (mainObject.getString("status").equals("ok")) {
//                        JSONObject data = response.getJSONObject("data");
                        JSONArray array = mainObject.getJSONArray("articles");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);

                            Log.e("source", "onResponse: " + object.toString());
                            NewsHeadlines news = new NewsHeadlines(
                                    new Source("null", object.getJSONObject("source").getString("name")),
                                    object.getString("author"),
                                    object.getString("title"),
                                    object.getString("description"),
                                    object.getString("url"),
                                    object.getString("urlToImage"),
                                    object.getString("publishedAt"),
                                    object.getString("content")
                            );
                            newsList.add(news);
                        }
                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(MainActivity.this, "something went wrong ", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);

    }

    @Override
    public void onClick(View view) {
        LinearLayout button = (LinearLayout) view;
//        button.setBackground(R.drawable.circuler_slect_bg);
//        button.setBackgroundColor(0);
//        button.setBackgroundColor(R.drawable.circuler_slect_bg);
        newsList.clear();
        String category = button.getTag().toString();
        String cat = "https://saurav.tech/NewsAPI/top-headlines/category/" + category + "/us.json";
        getNews(cat);
        switch (view.getId()) {
            case R.id.general:
                binding.generall.setBackgroundResource(R.drawable.circuler_slect_bg);
                binding.sportss.setBackgroundResource(R.drawable.circuler_bg);
                binding.entertainmentt.setBackgroundResource(R.drawable.circuler_bg);
                binding.healthh.setBackgroundResource(R.drawable.circuler_bg);
                binding.businesss.setBackgroundResource(R.drawable.circuler_bg);
                binding.technologyy.setBackgroundResource(R.drawable.circuler_bg);
                binding.sciencee.setBackgroundResource(R.drawable.circuler_bg);
                break;
            case R.id.sports:
                binding.generall.setBackgroundResource(R.drawable.circuler_bg);
                binding.sportss.setBackgroundResource(R.drawable.circuler_slect_bg);
                binding.entertainmentt.setBackgroundResource(R.drawable.circuler_bg);
                binding.healthh.setBackgroundResource(R.drawable.circuler_bg);
                binding.businesss.setBackgroundResource(R.drawable.circuler_bg);
                binding.technologyy.setBackgroundResource(R.drawable.circuler_bg);
                binding.sciencee.setBackgroundResource(R.drawable.circuler_bg);
                break;
            case R.id.entertainment:
                binding.generall.setBackgroundResource(R.drawable.circuler_bg);
                binding.sportss.setBackgroundResource(R.drawable.circuler_bg);
                binding.entertainmentt.setBackgroundResource(R.drawable.circuler_slect_bg);
                binding.healthh.setBackgroundResource(R.drawable.circuler_bg);
                binding.businesss.setBackgroundResource(R.drawable.circuler_bg);
                binding.technologyy.setBackgroundResource(R.drawable.circuler_bg);
                binding.sciencee.setBackgroundResource(R.drawable.circuler_bg);
                break;
            case R.id.health:
                binding.generall.setBackgroundResource(R.drawable.circuler_bg);
                binding.sportss.setBackgroundResource(R.drawable.circuler_bg);
                binding.entertainmentt.setBackgroundResource(R.drawable.circuler_bg);
                binding.healthh.setBackgroundResource(R.drawable.circuler_slect_bg);
                binding.businesss.setBackgroundResource(R.drawable.circuler_bg);
                binding.technologyy.setBackgroundResource(R.drawable.circuler_bg);
                binding.sciencee.setBackgroundResource(R.drawable.circuler_bg);
                break;
            case R.id.business:
                binding.generall.setBackgroundResource(R.drawable.circuler_bg);
                binding.sportss.setBackgroundResource(R.drawable.circuler_bg);
                binding.entertainmentt.setBackgroundResource(R.drawable.circuler_bg);
                binding.healthh.setBackgroundResource(R.drawable.circuler_bg);
                binding.businesss.setBackgroundResource(R.drawable.circuler_slect_bg);
                binding.technologyy.setBackgroundResource(R.drawable.circuler_bg);
                binding.sciencee.setBackgroundResource(R.drawable.circuler_bg);
                break;
            case R.id.technology:
                binding.generall.setBackgroundResource(R.drawable.circuler_bg);
                binding.sportss.setBackgroundResource(R.drawable.circuler_bg);
                binding.entertainmentt.setBackgroundResource(R.drawable.circuler_bg);
                binding.healthh.setBackgroundResource(R.drawable.circuler_bg);
                binding.businesss.setBackgroundResource(R.drawable.circuler_bg);
                binding.technologyy.setBackgroundResource(R.drawable.circuler_slect_bg);
                binding.sciencee.setBackgroundResource(R.drawable.circuler_bg);
                break;
            case R.id.science:
                binding.generall.setBackgroundResource(R.drawable.circuler_bg);
                binding.sportss.setBackgroundResource(R.drawable.circuler_bg);
                binding.entertainmentt.setBackgroundResource(R.drawable.circuler_bg);
                binding.healthh.setBackgroundResource(R.drawable.circuler_bg);
                binding.businesss.setBackgroundResource(R.drawable.circuler_bg);
                binding.technologyy.setBackgroundResource(R.drawable.circuler_bg);
                binding.sciencee.setBackgroundResource(R.drawable.circuler_slect_bg);
                break;

            default:
                button.setBackgroundResource(R.drawable.circuler_bg);



        }

    }
}