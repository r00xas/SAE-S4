package com.example.scanmed;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String fragmentName = getIntent().getStringExtra("fragment");

        if ("HomepageFrag".equals(fragmentName)) {
            // Display HomepageFrag
            Intent intent = new Intent(MainActivity.this, HomepageActivity.class);
            startActivity(intent);
        }
    }
}