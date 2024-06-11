package com.example.scanmed;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ResetPasswordActivity extends AppCompatActivity {

    private ImageButton backButton;
    private Button resetButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        loadLocale();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_reset_password); // Assurez-vous que ce fichier existe

        setupViews();
        setupListeners();
    }

    private void setupViews() {
        backButton = findViewById(R.id.imageButton2);
        resetButton = findViewById(R.id.buttonReset);
    }

    private void setupListeners() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToActivity(ResetPasswordActivity.class);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToActivity(CreatePasswordActivity.class);
            }
        });
    }

    private void navigateToActivity(Class<?> activityClass) {
        Intent intent = new Intent(ResetPasswordActivity.this, activityClass);
        startActivity(intent);
    }

    public void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocal(this, language);
    }

    public void setLocal(Activity activity, String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

        SharedPreferences.Editor editor = activity.getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", langCode);
        editor.apply();
    }
}
