package com.example.scanmed;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {
    private static final int NB_CHARAC_PASSWORD = 8;
    private boolean isLanguageChanged = false;
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    private EditText ETT_username;
    private EditText ETT_mail;
    private EditText ETT_password;
    private CheckBox id_CheckBox1;
    private CheckBox id_CheckBox2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadLocale();
        EdgeToEdge.enable(this);
        setContentView(R.layout.fragment_sign_in);
        super.onCreate(savedInstanceState);

        Spinner spinner = findViewById(R.id.spinner);
        String[] options = {"English","Français"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_layout, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        if (language.equals("en")) {
            spinner.setSelection(0);
        } else if (language.equals("fr")) {
            spinner.setSelection(1);
        }

        ETT_username = findViewById(R.id.ETT_username);
        ETT_mail = findViewById(R.id.ETT_mail);
        ETT_password = findViewById(R.id.ETT_password);
        id_CheckBox1 = findViewById(R.id.id_CheckBox1);
        id_CheckBox2 = findViewById(R.id.id_CheckBox2);


        TextView TV_Login = findViewById(R.id.TV_login);

        TV_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, HomepageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLanguage = (position == 0) ? "en" : "fr";
                SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
                String currentLanguage = prefs.getString("My_Lang", "");

                if (!selectedLanguage.equals(currentLanguage)) {
                    setLocale(selectedLanguage);
                    isLanguageChanged = true;
                    restartActivity();
                } else {
                    isLanguageChanged = false;
                }
                Underline_Text(TV_Login);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button button_singin = findViewById(R.id.button);

        button_singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset_EditText_CheckBox();
                if (Check_sign_In()){
                    send_to_back();
                    Intent intent = new Intent(SignInActivity.this, HomeMenuActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void reset_EditText_CheckBox() {
        Drawable drawable = ContextCompat.getDrawable(this,R.drawable.rectangle_shape);
        ETT_username.setBackground(drawable);
        ETT_mail.setBackground(drawable);
        ETT_password.setBackground(drawable);
        id_CheckBox1.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.logo)));
        id_CheckBox2.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.logo)));
    }

    private void send_to_back() {
        /**
         * fonction qui envoie au back les infos pour créer un nouveau compte
         */
    }

    private void restartActivity() {
        finish();
        startActivity(getIntent());
    }

    public void setLocale(String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", langCode);
        editor.apply();
    }

    public void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }


    private void Underline_Text(TextView TV){
        String text = TV.getText().toString();
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new UnderlineSpan(), 0, text.length(), 0);
        TV.setText(spannableString);
    }

    private boolean Check_sign_In() {
        boolean everythingCheck = true;

        if (!Check_username(ETT_username)) {
            Show_Error_SignIn(4);
            everythingCheck = false;
        }
        if (!Check_mail(ETT_mail)) {
            Show_Error_SignIn(3);
            everythingCheck = false;
        }
        if (!Check_password(ETT_password)) {
            Show_Error_SignIn(2);
            everythingCheck = false;
        }
        if (!id_CheckBox1.isChecked()) {
            Show_Error_SignIn(1);
            everythingCheck = false;
        }
        if (!id_CheckBox2.isChecked()) {
            Show_Error_SignIn(0);
            everythingCheck = false;
        }

        if(!everythingCheck)Toast.makeText(this, R.string.Error_SignIn, Toast.LENGTH_SHORT).show();


        return everythingCheck;
    }


    private boolean Check_username(EditText ettUsername) {
        return !ettUsername.getText().toString().isEmpty();
    }

    private boolean Check_mail(EditText ettMail) {
        String mail = ettMail.getText().toString();

        if(mail == null || mail.equals("")){
            return false;
        }
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();

        /*
        if(matcher.matches()){
            //envoie API
        }
         */
        /**
         * connexion au back pour vérifier si pas déjà utilisé
         */


    }

    private boolean Check_password(EditText ettPassword) {
        String password = ettPassword.getText().toString();
        if(password == null || password.equals("")){
            return false;
        }
        if(password.length() < NB_CHARAC_PASSWORD){
            return false;
        }
        return true;
    }


    /**
     * nb = 0 -> erreur de CheckBox, id_CheckBox2
     * nb = 1 -> erreur de CheckBox, id_CheckBox1
     * nb = 2 -> erreur de mot de passe, ETT_password
     * nb = 3 -> erreur de mail, ETT_mail
     * nb = 4 -> erreur de pseudo, ETT_username
     * @param nb
     */
    private void Show_Error_SignIn(int nb){
        Drawable drawable = ContextCompat.getDrawable(this,R.drawable.rectangle_shape_border_red);
        switch (nb){
            case 0 :
                id_CheckBox2.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                break;
            case 1 :
                id_CheckBox1.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                break;
            case 2 :
                ETT_password.setBackground(drawable);
                break;
            case 3 :
                ETT_mail.setBackground(drawable);
                break;
            case 4 :
                ETT_username.setBackground(drawable);
                break;
            default:
                return;
        }
    }
}