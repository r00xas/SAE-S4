package com.example.scanmed;

import static android.app.PendingIntent.getActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.scanmed.databinding.FragmentHomepageBinding;

import java.util.Locale;

public class HomepageActivity extends AppCompatActivity {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private boolean isLanguageChanged = false;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    // TODO: Rename and change types and number of parameters
    public void onCreate(Bundle savedInstanceState) {
        loadLocale();
        EdgeToEdge.enable(this);
        setContentView(R.layout.fragment_homepage);
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Find the button by its ID
        Button btnConnexion = findViewById(R.id.buttonLogin);

        // Set a click listener on the button
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start a fragment transaction to replace HomepageFrag with LoginFragment
                Intent intent = new Intent(HomepageActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Find the button by its ID
        Button btnInscription = findViewById(R.id.buttonSignIn);

        // Set a click listener on the button
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SignInActivity
                Intent intent = new Intent(HomepageActivity.this, SignInActivity.class);
                startActivity(intent);

            }
        });

    }

    private void restartActivity() {
        finish();
        startActivity(getIntent());
    }

    private void Underline_Text(TextView TV){
        String text = TV.getText().toString();
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new UnderlineSpan(), 0, text.length(), 0);
        TV.setText(spannableString);
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
}