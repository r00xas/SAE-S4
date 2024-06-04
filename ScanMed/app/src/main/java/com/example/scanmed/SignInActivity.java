package com.example.scanmed;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class SignInActivity extends AppCompatActivity {
    private static final int NB_CHARAC_PASSWORD = 8;
    private boolean isLanguageChanged = false;

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

        TextView TV_Login = findViewById(R.id.TV_login);

        TV_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, HomepageFrag.class);
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

    private boolean Check_sign_In(){
        EditText ETT_username = findViewById(R.id.ETT_username);
        EditText ETT_mail = findViewById(R.id.ETT_mail);
        EditText ETT_password = findViewById(R.id.ETT_password);
        CheckBox id_CheckBox1 = findViewById(R.id.id_CheckBox1);
        CheckBox id_CheckBox2 = findViewById(R.id.id_CheckBox2);
        boolean everythinkCheck = true;

        /*if(!Check_username(ETT_username) || !Check_mail(ETT_mail) || !Check_password(ETT_password) || !Check_CheckBox(id_CheckBox1, id_CheckBox2)){
            everythinkCheck = false;
            Show_Error_SignIn();
        }*/
        return everythinkCheck;
    }

    private boolean Check_username(EditText ettUsername) {
        return ettUsername.getText().toString().isEmpty();
    }

    private boolean Check_mail(EditText ettMail) {
        String mail = ettMail.getText().toString();
        if(mail.isEmpty() || !(mail.contains("@") && mail.contains(".")) && countOccurrences(mail,'@') == 1){
            return false;
        }


        //connexion au back pour vérifier si pas déjà utilisé

        return true;
    }

    //ajouter des caractères à ne pas utiliser
    private boolean Check_password(EditText ettPassword) {
        String password = ettPassword.getText().toString();
        return (!(password.isEmpty()) && (password.length() > NB_CHARAC_PASSWORD) && !((password.contains("<")) && (password.contains(">")) && (password.contains("+")) && (password.contains("*"))));
    }

    //Voir si il faut que les deux soient check ou pas
    private boolean Check_CheckBox(CheckBox idCheckBox1, CheckBox idCheckBox2) {
        return idCheckBox1.isChecked() && idCheckBox2.isChecked();
    }

    private void Show_Error_SignIn(){
        Toast.makeText(this, R.string.Error_SignIn, Toast.LENGTH_SHORT).show();
    }

    public static int countOccurrences(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }


}