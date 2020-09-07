package com.news.reporter;

import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private EditText mEdit;
    public TextView selectedText;
    private ImageButton mButton;
    private static final String API_KEY = "229e28237bb54c44800483085d646181";

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
                makeApiCall(editText);
            }
        });

    }

    public void makeApiCall(String text)    {
        Calendar c = Calendar.getInstance();
        int D = c.get(Calendar.DAY_OF_MONTH)-1;
        int Y = c.get(Calendar.YEAR)-1;
        int M = c.get(Calendar.MONTH)-1;
        String url = "http://newsapi.org/v2/everything?q="+text+"&from=2020-08-07"+Y+"-"+M+"-"+D+"-"+"&sortBy=publishedAt&apiKey="+API_KEY;
        RequestQueue mQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String status = response.getString("status");
                    String no = response.getString("totalResults");
                    Toast.makeText(MainActivity.this, "status: "+status+" no: "+no, Toast.LENGTH_SHORT).show();
                    mEdit.setText("status: "+status+" no: "+no);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

    }
}