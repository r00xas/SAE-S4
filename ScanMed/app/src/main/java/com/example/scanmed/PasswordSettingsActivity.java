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
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.regex.Pattern;

public class PasswordSettingsActivity extends AppCompatActivity {

    private EditText textPassword1;
    private EditText textPassword2;
    private EditText textPassword3;
    private LinearLayout layoutPassword1;
    private LinearLayout layoutPassword2;
    private LinearLayout layoutPassword3;
    private ImageButton seePassword1;
    private ImageButton seePassword2;
    private ImageButton seePassword3;
    private Button buttonResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadLocale();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_password_settings); // Assurez-vous que ce fichier existe

        setupViews();
        setupListeners();
    }

    private void setupViews() {
        textPassword1 = findViewById(R.id.TextPassword1);
        textPassword2 = findViewById(R.id.TextPassword2);
        textPassword3 = findViewById(R.id.TextPassword3);
        layoutPassword1 = findViewById(R.id.LayoutPassword1);
        layoutPassword2 = findViewById(R.id.LayoutPassword2);
        layoutPassword3 = findViewById(R.id.LayoutPassword3);
        seePassword1 = findViewById(R.id.seePassword1);
        seePassword2 = findViewById(R.id.seePassword2);
        seePassword3 = findViewById(R.id.seePassword3);
        buttonResetPassword = findViewById(R.id.buttonResetPassword);
    }

    private void setupListeners() {
        ImageButton backButton = findViewById(R.id.imageButton3);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToActivity(AccountSettingsModifyActivity.class);
            }
        });

        seePassword1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePasswordVisibility(textPassword1, seePassword1);
            }
        });

        seePassword2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePasswordVisibility(textPassword2, seePassword2);
            }
        });

        seePassword3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePasswordVisibility(textPassword3, seePassword3);
            }
        });

        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                String currentPwd = textPassword1.getText().toString();
                String newPwd = textPassword2.getText().toString();
                String confirmationPwd = textPassword3.getText().toString();
                if (!checkGoodPassword(currentPwd)) {
                    layoutPassword1.setBackground(getResources().getDrawable(R.drawable.input_text_style_error));
                } else if (!passwordRulesCheck(newPwd)) {
                    layoutPassword2.setBackground(getResources().getDrawable(R.drawable.input_text_style_error));
                } else if (!newPwd.equals(confirmationPwd)) {
                    layoutPassword3.setBackground(getResources().getDrawable(R.drawable.input_text_style_error));
                } else {
                    // Action à réaliser si tout est correct
                }
            }
        });
    }

    private boolean checkGoodPassword(String pwd) {
        SharedPreferences sharedPref = getSharedPreferences("my_preferences", MODE_PRIVATE);
        Log.i("API", sharedPref.getString("password", "none"));
        Log.i("API", pwd);
        Log.i("API", "Même mot de passe: " + pwd.equals(sharedPref.getString("password", "none")));
        return pwd.equals(sharedPref.getString("password", "none"));
    }

    private boolean passwordRulesCheck(String pwd) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,64}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(pwd).matches();
    }

    private void togglePasswordVisibility(EditText editText, ImageButton imageButton) {
        int inputType = editText.getInputType();
        if (inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
            imageButton.setImageResource(R.drawable.eye);
        } else {
            inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
            imageButton.setImageResource(R.drawable.close_eyes);
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
}
