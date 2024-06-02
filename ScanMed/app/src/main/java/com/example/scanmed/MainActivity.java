package com.example.scanmed;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String fragmentName = getIntent().getStringExtra("fragment");

        if ("HomepageFrag".equals(fragmentName)) {
            // Display HomepageFrag
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomepageFrag())
                    .commit();
        }
    }
}