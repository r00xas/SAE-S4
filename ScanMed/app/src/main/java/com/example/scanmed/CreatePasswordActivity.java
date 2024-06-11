package com.example.scanmed;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.regex.Pattern;

public class CreatePasswordActivity extends AppCompatActivity {

    private EditText textPassword1;
    private EditText textPassword2;
    private ImageButton seePassword1;
    private ImageButton seePassword2;
    private Button buttonAskReset;

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
        seePassword1 = findViewById(R.id.seePassword1);
        seePassword2 = findViewById(R.id.seePassword2);
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

        buttonAskReset.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                String newPwd = textPassword1.getText().toString();
                String confirmationpwd = textPassword2.getText().toString();
                if (!passwordRulesCheck(newPwd)) {
                    textPassword1.setBackground(getResources().getDrawable(R.drawable.input_text_style_error));
                } else if (!newPwd.equals(confirmationpwd)){
                    textPassword2.setBackground(getResources().getDrawable(R.drawable.input_text_style_error));
                } else {
                    Log.i("API", "connexion");
                    navigateToActivity(PasswordChangedActivity.class);
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
        Intent intent = new Intent(CreatePasswordActivity.this, activityClass);
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
