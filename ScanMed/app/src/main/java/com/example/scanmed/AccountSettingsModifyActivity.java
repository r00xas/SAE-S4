package com.example.scanmed;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class AccountSettingsModifyActivity extends AppCompatActivity {

    private EditText editUserText;
    private EditText editMailText;
    private EditText editPasswordText;
    private LinearLayout editMailLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        loadLocale();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_account_settings_modify); // Assurez-vous que ce fichier existe

        setupViews();
        setupListeners();

        SharedPreferences sharedPref = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username", "toto");
        editor.putString("password", "Ab=1aaaa");
        editor.putString("mail", "e.launay512@gmail.com");
        editor.apply();
    }

    private void setupViews() {
        editUserText = findViewById(R.id.editUserText);
        editMailText = findViewById(R.id.editMailText);
        editPasswordText = findViewById(R.id.editPasswordText);
        editMailLayout = findViewById(R.id.editMailLayout);

        SharedPreferences sharedPref = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);

        editUserText.setText(sharedPref.getString("username", "none"));
        editMailText.setText(sharedPref.getString("mail", "none"));
        editPasswordText.setText("tototototototo");
    }

    private void setupListeners() {
        ImageButton backButton1 = findViewById(R.id.backButton1);
        ImageButton editPassword = findViewById(R.id.editPassword);
        Button buttonReset = findViewById(R.id.buttonReset);

        backButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToActivity(AccountSettingsActivity.class);
            }
        });

        editPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToActivity(PasswordSettingsActivity.class);
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                if (editMailText.getText().toString().contains("@"))
                    save();
                else {
                    editMailLayout.setBackground(getResources().getDrawable(R.drawable.input_text_style_error));
                }
            }
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void save() {
        SharedPreferences sharedPref = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username", editUserText.getText().toString());
        editor.putString("password", editPasswordText.getText().toString());
        editor.putString("mail", editMailText.getText().toString());
        editor.apply();
        editMailLayout.setBackground(getResources().getDrawable(R.drawable.input_text_style));
    }

    private void navigateToActivity(Class<?> activityClass) {
        Intent intent = new Intent(AccountSettingsModifyActivity.this, activityClass);
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
