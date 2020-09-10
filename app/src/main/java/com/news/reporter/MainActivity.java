package com.news.reporter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private EditText mEdit;
    public TextView selectedText;
    private ImageButton mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdit = findViewById(R.id.mEdit);
        selectedText = findViewById(R.id.something);
        mButton = findViewById(R.id.mButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editText = mEdit.getText().toString();
                selectedText.setText(editText);
                jumpToNewsActivity(editText);
            }
        });

    }

    /*public void makeApiCall(String text)    {
        Calendar c = Calendar.getInstance();
        int D = c.get(Calendar.DAY_OF_MONTH)-1;
        int Y = c.get(Calendar.YEAR)-1;
        int M = c.get(Calendar.MONTH)-1;

        final List<NewsContent> contentList = new ArrayList<>();

        String url = "https://newsapi.org/v2/everything?q="+text+"&from="+Y+"-"+M+"-"+D+"-"+"&sortBy=publishedAt&apiKey="+API_KEY;
        RequestQueue mQueue = Volley.newRequestQueue(this);
        Toast.makeText(this, "AA GAYA ANDAR", Toast.LENGTH_SHORT).show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String status = response.getString("status");
                    String no = response.getString("totalResults");
                    Toast.makeText(MainActivity.this, "status: "+status+" no: "+no, Toast.LENGTH_SHORT).show();
                    JSONArray articles = response.getJSONArray("articles");
                    for(int i = 0; i < articles.length(); i++)  {
                        if(i >= 10)
                            break;
                        JSONObject main = articles.getJSONObject(i);
                        String title = main.getString("title");
                        String link = main.getString("url");
                        String imgUrl = main.getString("urlToImage");
                        JSONObject s = main.getJSONObject("source");
                        String source = s.getString("name");
                        NewsContent contentSetter = new NewsContent(title, imgUrl, link, source);
                        contentList.add(contentSetter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "GADBAD ho gayi"+error.getCause(), Toast.LENGTH_SHORT).show();
                    }
                });
        mQueue.add(request);
    }*/

    public void jumpToNewsActivity(String text) {
        Intent intent = new Intent(this, NewsActivity.class);
        intent.putExtra("searchQuery", text);
        startActivity(intent);
    }
}