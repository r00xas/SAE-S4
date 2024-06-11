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
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class AccountSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        loadLocale();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        setupClickListeners();
    }

    private void setupClickListeners() {
        ImageButton backButton1 = findViewById(R.id.backButton1);
        ImageButton imageButton = findViewById(R.id.imageButton);
        LinearLayout settings = findViewById(R.id.Settings);
        LinearLayout cgu = findViewById(R.id.CGU);
        LinearLayout helpCenter = findViewById(R.id.HelpCenter);
        Button buttonSavePassword = findViewById(R.id.buttonSavePassword);

        backButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToActivity(HomeMenuActivity.class);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToActivity(Fragment_edit_picture.class);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToActivity(AccountSettingsModifyActivity.class);
            }
        });

        cgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToActivity(Terms_of_use.class);
            }
        });

        helpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToActivity(HelpCenterFragment.class);
            }
        });

        buttonSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //navigateToActivity(PasswordSettingsActivity.class);
            }
        });
    }

    private void navigateToActivity(Class<?> activityClass) {
        Intent intent = new Intent(AccountSettingsActivity.this, activityClass);
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
