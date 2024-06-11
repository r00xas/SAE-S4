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

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class PasswordModifiedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadLocale();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_password_modified);

        ImageButton imageButton = findViewById(R.id.imageButton);
        Button buttonBackHome = findViewById(R.id.buttonBackHome);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigue vers AccountSettingsModifyActivity
                Intent intent = new Intent(PasswordModifiedActivity.this, AccountSettingsModifyActivity.class);
                startActivity(intent);
            }
        });

        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigue vers AccountSettingsModifyActivity
                Intent intent = new Intent(PasswordModifiedActivity.this, AccountSettingsModifyActivity.class);
                startActivity(intent);
            }
        });
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
