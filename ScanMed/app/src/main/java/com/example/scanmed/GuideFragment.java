package com.example.scanmed;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class GuideFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadLocale();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_guide);
        EdgeToEdge.enable(this);
        TextView summarySection1 = findViewById(R.id.summary_section1);
        TextView summarySection2 = findViewById(R.id.summary_section2);
        TextView summarySection3 = findViewById(R.id.summary_section3);
        TextView summarySection4 = findViewById(R.id.summary_section4);
        final LinearLayout section1 = findViewById(R.id.section1);
        final LinearLayout section2 = findViewById(R.id.section2);
        final LinearLayout section3 = findViewById(R.id.section3);
        final LinearLayout section4 = findViewById(R.id.section4);

        // Définit les listeners de clic pour chaque élément du sommaire
        summarySection1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollToView(section1);
            }
        });

        summarySection2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollToView(section2);
            }
        });

        summarySection3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollToView(section3);
            }
        });

        summarySection4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollToView(section4);
            }
        });

        Button IMG_Back = findViewById(R.id.IMG_Back);
        IMG_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideFragment.this, HomeMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }


    private void scrollToView(View view) {
        final ScrollView scrollView = findViewById(R.id.scrollView);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(0, view.getTop());
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