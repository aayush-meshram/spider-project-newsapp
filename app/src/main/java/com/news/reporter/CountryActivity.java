package com.news.reporter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import java.util.ArrayList;

public class CountryActivity extends AppCompatActivity {

    public AutoCompleteTextView mACTV;
    public ImageButton mButtonSearch;

    public String[] country = {"Argentina", "Australia", "Austria", "Belgium", "Brazil", "Bulgaria",
        "Canada", "China", "Colombia", "Cuba", "Czech Republic", "Egypt", "France", "Germany", "Greece",
        "Hong Kong", "India", "Indonesia", "Ireland", "Israel", "Italy", "Japan", "Latvia", "Lithuania",
            "Malaysia", "Mexico", "Morocco", "Netherlands", "New Zealand", "Nigeria", "Norway",
        "Philippines", "Poland", "Portugal", "Romania", "Russia", "Saudi Arabia", "Serbia", "Singapore",
        "Slovakia", "Slovenia", "South Africa", "South Korea", "Sweden", "Switzerland", "Taiwan", "Thailand",
        "Turkey", "UAE", "Ukraine", "United Kingdom", "United States", "Venuzuela"};

    public String[] countryCode = {"ar", "au", "at", "be", "br", "bg", "ca", "cn", "co", "cu", "cz", "eg",
        "fr", "de", "gr", "hk", "in", "id", "ie", "il", "it", "jp", "lv", "lt", "my", "mx", "ma", "nl", "nz",
        "ng", "no", "ph", "pl", "pt", "ro", "ru", "sa", "rs", "sg", "sk", "si", "za", "kr", "se", "ch", "tw", "th",
        "tr", "ae", "ua", "gb", "us", "ve"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        mButtonSearch = findViewById(R.id.searchButton);
        mACTV = findViewById(R.id.searchText);

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(CountryActivity.this, android.R.layout.select_dialog_item, country);
        mACTV.setThreshold(1);
        mACTV.setAdapter(mAdapter);

        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = mACTV.getText().toString();

            }
        });
    }
}