package com.example.scanmed;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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

    }

    private void setupViews() {
        editUserText = findViewById(R.id.editUserText);
        editMailText = findViewById(R.id.editMailText);
        editPasswordText = findViewById(R.id.editPasswordText);
        editMailLayout = findViewById(R.id.editMailLayout);

        SharedPreferences sharedPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);

        editUserText.setText(sharedPref.getString("username", "none"));
        editMailText.setText(sharedPref.getString("email", "none"));
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
                    editUser();
                else {
                    editMailLayout.setBackground(getResources().getDrawable(R.drawable.input_text_style_error));
                }
            }
        });
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

    private void editUser() {

        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("authToken", null);
        String email = sharedPreferences.getString("email", null);

        if (token == null || email == null) {
            Log.e("API", "Token or email not found in SharedPreferences");
            return;
        }

        OkHttpClient client = new OkHttpClient();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", sharedPreferences.getString("username", null));
            jsonObject.put("email", sharedPreferences.getString("email", null));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("API", "JSON Exception: " + e.getMessage());
            return;
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());

        Request request = new Request.Builder()
                .url("http://192.168.1.13:8080/user/edit")
                .patch(requestBody)
                .addHeader("X-Email", email)
                .addHeader("X-Token", token)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                Log.e("API", "Request failed: " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.v("API", "dans reponse");

                String responseBody = response.body().string();
                Log.d("API", "Response body: " + responseBody);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful()) {
                            Log.i("API", "User modified successfully: " + responseBody);
                            SharedPreferences sharedPref = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("username", editUserText.getText().toString());
                            editor.putString("mail", editMailText.getText().toString());
                            editor.apply();
                            navigateToActivity(AccountSettingsActivity.class);
                        } else {
                            Log.e("API", "Error response body: " + responseBody);
                        }
                    }
                });
                response.close();
            }
        });
    }
}
