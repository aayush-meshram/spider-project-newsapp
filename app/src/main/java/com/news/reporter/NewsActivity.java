package com.news.reporter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.news.reporter.NewsContent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    public String search;
    public RecyclerView mRecyclerView;
    public ItemAdapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<NewsContent> myList = new ArrayList<>();
    private static final String API_KEY = "229e28237bb54c44800483085d646181";
    public boolean FLAG_UPDATE = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);
        Intent intent = getIntent();

        search = intent.getStringExtra("searchQuery");




        Log.i("Search Query", "onCreate: "+search);
        if(search == null)    {
            NewsContent ic = new NewsContent("You have been rickrolled", "src", "No link for you", "By yourself");
            myList.add(ic);
            addContentToRecyclerView(myList);
        }
        else    {
            makeApiCall(search, false);
            Toast.makeText(this, "else me ->"+myList, Toast.LENGTH_SHORT).show();
        }

        /*mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ItemAdapter(myList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);*/

    }

    public void makeApiCall(String text, final boolean forUpdate) {
        Calendar c = Calendar.getInstance();
        int D = c.get(Calendar.DAY_OF_MONTH) - 1;
        int Y = c.get(Calendar.YEAR) - 1;
        int M = c.get(Calendar.MONTH) - 1;

        String url = "https://newsapi.org/v2/everything?qInTitle=" + text + "&from=" + Y + "-" + M + "-" + D + "-" + "&sortBy=popularity&language=en&apiKey=" + API_KEY;
        RequestQueue mQueue = Volley.newRequestQueue(this);
        Toast.makeText(this, "AA GAYA ANDAR"+text, Toast.LENGTH_SHORT).show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    String status = response.getString("status");
                    String no = response.getString("totalResults");
                    Toast.makeText(NewsActivity.this, "status: " + status + " no: " + no, Toast.LENGTH_SHORT).show();
                    JSONArray articles = response.getJSONArray("articles");
                    for (int i = 0; i < articles.length(); i++) {
                        JSONObject main = articles.getJSONObject(i);
                        String title = main.getString("title");
                        String link = main.getString("url");
                        String imgUrl = main.getString("urlToImage");
                        JSONObject s = main.getJSONObject("source");
                        String source = s.getString("name");
                        NewsContent contentSetter = new NewsContent(title, imgUrl, link, source);
                        myList.add(contentSetter);
                        Log.i("myList", "dekh ->"+myList);
                    }
                    Log.i("for ke bahar", "myList"+myList);
                    addContentToRecyclerView(myList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NewsActivity.this, "GADBAD ho gayi" + error.getCause(), Toast.LENGTH_SHORT).show();
                    }
                });
        Log.i("makeAPICall", ""+myList);
        mQueue.add(request);
    }


    public void addContentToRecyclerView(ArrayList<NewsContent> LIST)   {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ItemAdapter(LIST);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String link = myList.get(position).getLink();
                Toast.makeText(NewsActivity.this, "Link :"+link, Toast.LENGTH_SHORT).show();
            }
        });
    }
}