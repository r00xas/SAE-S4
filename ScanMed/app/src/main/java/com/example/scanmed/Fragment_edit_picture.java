package com.example.scanmed;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Fragment_edit_picture extends AppCompatActivity {

    /**
     * Pas mal de nombre magique mais je sais pas comment trop réduire leur nombre
     */
    private int num_photo = -1;
    private ArrayList<ImageView> array_img;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private ImageView img6;
    private ImageView img7;
    private ImageView img8;
    private ImageView img9;
    private ImageView imgBIG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadLocale();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.fragment_edit_picture);

        // configuration des images
        config_img();


        Button button_save = findViewById(R.id.button_Save);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num_photo>=0){
                    save_new_picture(num_photo);
                }
            }

        });

        Button button_BACK = findViewById(R.id.IMG_Back);

        button_BACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fragment_edit_picture.this, AccountSettingsActivity.class);
                startActivity(intent);
                finish();            }
        });

        button_BACK.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button_BACK.setAlpha(0.5f);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        button_BACK.setAlpha(1.0f);
                        break;
                }
                return false;
            }
        });

    }

    private void config_img() {
        imgBIG = findViewById(R.id.IV_avatar);
        img1 = findViewById(R.id.IMG_A1);
        img2 = findViewById(R.id.IMG_A2);
        img3 = findViewById(R.id.IMG_A3);
        img4 = findViewById(R.id.IMG_A4);
        img5 = findViewById(R.id.IMG_A5);
        img6 = findViewById(R.id.IMG_A6);
        img7 = findViewById(R.id.IMG_A7);
        img8 = findViewById(R.id.IMG_A8);
        img9 = findViewById(R.id.IMG_A9);
        array_img = new ArrayList<>(Arrays.asList(img1,img2,img3,img4,img5,img6,img7,img8,img9));

        /**
         * Méthode barbare pour récupérer sur quel image la personne à cliquer
         * num_photo = photo sélectionné par la personne, de base à -1 (valeur imposible)
         * onClickListener sur chaque image qui va juste modifier num_photo et mettre le numéro de la photo qui a été sélectionné
         */

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verif_deja_select(1, img1)){
                    num_photo = 1;
                    reset_alpha_img();
                    img1.setImageAlpha(128);
                    imgBIG.setImageResource(R.drawable.pp_2_1);
                    Log.e("Ouais", String.valueOf(num_photo));
                }
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verif_deja_select(2, img2)){
                    num_photo = 2;
                    reset_alpha_img();
                    img2.setImageAlpha(128);
                    imgBIG.setImageResource(R.drawable.pp_2_2);
                    Log.e("Ouais", String.valueOf(num_photo));
                }
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verif_deja_select(3, img3)){
                    num_photo = 3;
                    reset_alpha_img();
                    img3.setImageAlpha(128);
                    imgBIG.setImageResource(R.drawable.pp_2_3);
                    Log.e("Ouais", String.valueOf(num_photo));
                }
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verif_deja_select(4, img4)){
                    num_photo = 4;
                    reset_alpha_img();
                    img4.setImageAlpha(128);
                    imgBIG.setImageResource(R.drawable.pp_3_1);
                    Log.e("Ouais", String.valueOf(num_photo));
                }
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verif_deja_select(5, img5)){
                    num_photo = 5;
                    reset_alpha_img();
                    img5.setImageAlpha(128);
                    imgBIG.setImageResource(R.drawable.pp_3_2);
                    Log.e("Ouais", String.valueOf(num_photo));
                }
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verif_deja_select(6, img6)){
                    num_photo = 6;
                    reset_alpha_img();
                    img6.setImageAlpha(128);
                    imgBIG.setImageResource(R.drawable.pp_3_3);
                    Log.e("Ouais", String.valueOf(num_photo));
                }
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verif_deja_select(7, img7)){
                    num_photo = 7;
                    reset_alpha_img();
                    img7.setImageAlpha(128);
                    imgBIG.setImageResource(R.drawable.pp_4_1);
                    Log.e("Ouais", String.valueOf(num_photo));
                }
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verif_deja_select(8, img8)){
                    num_photo = 8;
                    reset_alpha_img();
                    img8.setImageAlpha(128);
                    imgBIG.setImageResource(R.drawable.pp_4_2);
                    Log.e("Ouais", String.valueOf(num_photo));
                }
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verif_deja_select(9, img9)){
                    num_photo = 9;
                    reset_alpha_img();
                    img9.setImageAlpha(128);
                    imgBIG.setImageResource(R.drawable.pp_4_3);
                    Log.e("Ouais", String.valueOf(num_photo));
                }
            }
        });
    }

    private boolean verif_deja_select(int i, ImageView img) {
        if (num_photo == i){
            img.setImageAlpha(255);
            num_photo = -1;
            return true;
        }
        return false;
    }

    private void reset_alpha_img() {
        for(ImageView img : array_img){
            img.setImageAlpha(255);
        }
    }

    private void save_new_picture(int num_photo) {
        //envoie API du num de la photo choisi
    }

    public void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = "fr";
                //prefs.getString("My_Lang", "");
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