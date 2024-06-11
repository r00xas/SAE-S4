package com.example.scanmed;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PasswordSettingsActivity extends AppCompatActivity {

    private EditText textPassword1;
    private EditText textPassword2;
    private EditText textPassword3;
    private ImageButton seePassword1;
    private ImageButton seePassword2;
    private ImageButton seePassword3;
    private Button buttonAskReset;
    private LinearLayout layout3;
    private LinearLayout layout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadLocale();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create_password); // Assurez-vous que ce fichier existe

        setupViews();
        setupListeners();
    }

    private void setupViews() {
        textPassword1 = findViewById(R.id.TextPassword1);
        textPassword2 = findViewById(R.id.TextPassword2);
        textPassword3 = findViewById(R.id.TextPassword3);
        seePassword1 = findViewById(R.id.seePassword1);
        seePassword2 = findViewById(R.id.seePassword2);
        seePassword3 = findViewById(R.id.seePassword3);
        layout2 = findViewById(R.id.LayoutPassword2);
        layout3 = findViewById(R.id.LayoutPassword3);
        buttonAskReset = findViewById(R.id.buttonAskReset);
    }

    private void setupListeners() {
        seePassword1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePasswordVisibility(textPassword1);
            }
        });

        seePassword2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePasswordVisibility(textPassword2);
            }
        });

        seePassword3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePasswordVisibility(textPassword3);
            }
        });

        buttonAskReset.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                String newPwd = textPassword2.getText().toString();
                String confirmationpwd = textPassword3.getText().toString();
                if (!passwordRulesCheck(newPwd)) {
                    layout2.setBackground(getResources().getDrawable(R.drawable.input_text_style_error));
                } else if (!newPwd.equals(confirmationpwd)){
                    layout3.setBackground(getResources().getDrawable(R.drawable.input_text_style_error));
                } else {
                    JSONObject jsonObject = new JSONObject();
                    SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
                    try {
                        EditText EditTextToken = findViewById(R.id.TextPassword1);
                        EditText EditTextPassword = findViewById(R.id.TextPassword2);
                        jsonObject.put("email", sharedPreferences.getString("email", null));
                        jsonObject.put("token", EditTextToken.getText().toString());
                        jsonObject.put("newPassword", EditTextPassword.getText().toString());
                        passwordReset(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private boolean passwordRulesCheck(String pwd) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,64}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(pwd).matches();
    }

    private void togglePasswordVisibility(EditText editText) {
        int inputType = editText.getInputType();
        if (inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
        } else {
            inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
        }
        editText.setInputType(inputType);
        editText.setSelection(editText.getText().length());
    }

    private void navigateToActivity(Class<?> activityClass) {
        Intent intent = new Intent(PasswordSettingsActivity.this, activityClass);
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

    private void passwordReset(JSONObject jsonObject)  {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Request request = new Request.Builder()
                .url("http://192.168.1.13:8080/auth/password_reset")
                .post(requestBody)
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
                            Log.i("API", responseBody);
                            navigateToActivity(PasswordChangedActivity.class);
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
