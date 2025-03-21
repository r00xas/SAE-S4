package com.example.scanmed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadLocale();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);

        ImageButton button = findViewById(R.id.img_button_plus);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });

        ImageView IV_Settings = findViewById(R.id.IV_Settings);
        /**
         * Faut avoir la page settings
         */
        /*IV_Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, COMPLETER_ICI.class);
                startActivity(intent);
                finish();
            }
        });*/
        IV_Settings.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        IV_Settings.setImageAlpha(128);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        IV_Settings.setImageAlpha(255);
                        break;
                }
                return false;
            }
        });

        ImageView IV_Help = findViewById(R.id.IV_Help);
        IV_Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, HelpCenterFragment.class);
                startActivity(intent);
                finish();
            }
        });

        IV_Help.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        IV_Help.setImageAlpha(128);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        IV_Help.setImageAlpha(255);
                        break;
                }
                return false;
            }
        });

        ImageView IV_Home = findViewById(R.id.IV_Home);

        LinearLayout LL_Home = findViewById(R.id.LL_Home);
        LL_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, HomeMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        LL_Home.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        IV_Home.setImageAlpha(128);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        IV_Home.setImageAlpha(255);
                        break;
                }
                return false;
            }
        });

        ImageView IV_History = findViewById(R.id.IV_History);

        LinearLayout LL_History = findViewById(R.id.LL_History);
        LL_History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, HistoryActivity.class);
                startActivity(intent);
                finish();
            }
        });

        LL_History.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        IV_History.setImageAlpha(128);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        IV_History.setImageAlpha(255);
                        break;
                }
                return false;
            }
        });

        Set_Element_Ongoing();

    }

    private void showPopupMenu(View anchor) {
        // Inflate the popup menu layout
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup, null);

        // Create the PopupWindow
        final PopupWindow popupWindow = new PopupWindow(popupView,
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                true);

        // Set up click listeners for the popup menu options
        popupView.findViewById(R.id.option1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle option 1 click
                popupWindow.dismiss();
            }
        });

        popupView.findViewById(R.id.option2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle option 2 click
                popupWindow.dismiss();
            }
        });

        popupView.findViewById(R.id.option3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle option 3 click
                popupWindow.dismiss();
            }
        });

        // Show the popup menu above the button
        int[] location = new int[2];
        anchor.getLocationOnScreen(location);
        popupWindow.showAtLocation(anchor, 0, location[0], location[1] - popupWindow.getHeight());
    }

    private void Set_Element_Ongoing(){
        /**appel API qui donne les éléments*/
        LinearLayout linear_layout_spawn = findViewById(R.id.linear_layout_spawn);
        ArrayList<HashMap<String,String>> elements = pour_test();
        for (HashMap<String, String> element : elements) {
            RelativeLayout newRelative = Create_Element(element);
            linear_layout_spawn.addView(newRelative);
        }
    }

    private ArrayList<HashMap<String, String>> pour_test() {
        ArrayList<HashMap<String, String>> elements = new ArrayList<>();

        HashMap<String, String> premierElement = new HashMap<>();
        premierElement.put("medicament","Paracétamol");
        premierElement.put("pharmacie", "Pharmacie Centrale");
        premierElement.put("date", "2024-05-01");
        premierElement.put("verification", "submitted");
        elements.add(premierElement);

        HashMap<String, String> deuxiemeElement = new HashMap<>();
        deuxiemeElement.put("medicament", "Ibuprofène");
        deuxiemeElement.put("pharmacie", "Pharmacie du Coin");
        deuxiemeElement.put("date", "2024-05-03");
        deuxiemeElement.put("verification", "rejected");
        elements.add(deuxiemeElement);

        HashMap<String, String> troisiemeElement = new HashMap<>();
        troisiemeElement.put("medicament", "Aspirine");
        troisiemeElement.put("pharmacie", "Pharmacie Rapide");
        troisiemeElement.put("date", "2024-04-30");
        troisiemeElement.put("verification", "resupplying");
        elements.add(troisiemeElement);

        HashMap<String, String> quatriemeElement = new HashMap<>();
        quatriemeElement.put("medicament", "Aspirine");
        quatriemeElement.put("pharmacie", "Pharmacie Rapide");
        quatriemeElement.put("date", "2024-04-30");
        quatriemeElement.put("verification", "resupplied");
        elements.add(quatriemeElement);

        HashMap<String, String> cinquiemeElement = new HashMap<>();
        cinquiemeElement.put("medicament", "Ibuprofène");
        cinquiemeElement.put("pharmacie", "Pharmacie du Coin");
        cinquiemeElement.put("date", "2024-05-03");
        cinquiemeElement.put("verification", "rejected");
        elements.add(cinquiemeElement);

        HashMap<String, String> sixiemeElement = new HashMap<>();
        sixiemeElement.put("medicament", "Ibuprofène");
        sixiemeElement.put("pharmacie", "Pharmacie du Coin");
        sixiemeElement.put("date", "2024-05-03");
        sixiemeElement.put("verification", "rejected");
        elements.add(sixiemeElement);

        HashMap<String, String> septiemeElement = new HashMap<>();
        septiemeElement.put("medicament", "Ibuprofène");
        septiemeElement.put("pharmacie", "Pharmacie du Coin");
        septiemeElement.put("date", "2024-05-03");
        septiemeElement.put("verification", "rejected");
        elements.add(septiemeElement);

        HashMap<String, String> huitiemeElement = new HashMap<>();
        huitiemeElement.put("medicament", "Ibuprofène");
        huitiemeElement.put("pharmacie", "Pharmacie du Coin");
        huitiemeElement.put("date", "2024-05-03");
        huitiemeElement.put("verification", "rejected");
        elements.add(huitiemeElement);

        HashMap<String, String> neuviemeElement = new HashMap<>();
        neuviemeElement.put("medicament", "Ibuprofène");
        neuviemeElement.put("pharmacie", "Pharmacie du Coin");
        neuviemeElement.put("date", "2024-05-03");
        neuviemeElement.put("verification", "rejected");
        elements.add(neuviemeElement);

        return elements;

    }

    private RelativeLayout Create_Element(HashMap<String, String> hm) {
        String medoc = hm.get("medicament");
        String pharma = hm.get("pharmacie");
        String date = hm.get("date");
        String etat = hm.get("verification");




        RelativeLayout relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 77, getResources().getDisplayMetrics())
        );
        layoutParams.setMargins(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 17, getResources().getDisplayMetrics())
        );
        relativeLayout.setLayoutParams(layoutParams);
        relativeLayout.setBackgroundResource(R.drawable.background_spawner);
        relativeLayout.setElevation(5);

        TextView textViewTop = new TextView(this);
        textViewTop.setId(View.generateViewId());
        RelativeLayout.LayoutParams paramsTop = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        paramsTop.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        textViewTop.setLayoutParams(paramsTop);
        textViewTop.setPadding(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()),
                0,
                0
        );
        textViewTop.setText(medoc);
        textViewTop.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        textViewTop.setTextColor(ContextCompat.getColor(this, R.color.black));
        textViewTop.setTypeface(ResourcesCompat.getFont(this, R.font.poppins_semibold));
        relativeLayout.addView(textViewTop);

        TextView textViewCenter = new TextView(this);
        textViewCenter.setId(View.generateViewId());
        RelativeLayout.LayoutParams paramsCenter = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        paramsCenter.addRule(RelativeLayout.CENTER_VERTICAL);
        textViewCenter.setLayoutParams(paramsCenter);
        textViewCenter.setPadding(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10.5F, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7.5F, getResources().getDisplayMetrics()),
                0,
                0
        );
        textViewCenter.setText(pharma);
        textViewCenter.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        textViewCenter.setTextColor(ContextCompat.getColor(this, R.color.black));
        textViewCenter.setTypeface(ResourcesCompat.getFont(this, R.font.poppins_regular));
        relativeLayout.addView(textViewCenter);

        TextView textViewBottom = new TextView(this);
        textViewBottom.setId(View.generateViewId());
        RelativeLayout.LayoutParams paramsBottom = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        paramsBottom.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        textViewBottom.setLayoutParams(paramsBottom);
        textViewBottom.setPadding(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()),
                0,
                0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7, getResources().getDisplayMetrics())
        );
        textViewBottom.setText(date);
        textViewBottom.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
        textViewBottom.setTextColor(ContextCompat.getColor(this, R.color.black));
        textViewBottom.setTypeface(ResourcesCompat.getFont(this, R.font.poppins_light));
        relativeLayout.addView(textViewBottom);

        if (etat.equals("submitted")){
            RelativeLayout innerRelativeLayout = new RelativeLayout(this);
            RelativeLayout.LayoutParams innerLayoutParams = new RelativeLayout.LayoutParams(
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics())
            );
            innerLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            innerLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            innerLayoutParams.setMargins(
                    0,
                    0,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 27, getResources().getDisplayMetrics()),
                    0
            );
            innerRelativeLayout.setLayoutParams(innerLayoutParams);
            innerRelativeLayout.setBackgroundResource(R.drawable.background_verification);
            innerRelativeLayout.setElevation(2);

            TextView verificationTextView = new TextView(this);
            RelativeLayout.LayoutParams verificationParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );

            verificationParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            verificationTextView.setLayoutParams(verificationParams);

            verificationTextView.setPadding(
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics())
            );

            verificationTextView.setText("Verification");
            verificationTextView.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
            verificationTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            verificationTextView.setTypeface(null, Typeface.BOLD);
            verificationTextView.setGravity(Gravity.CENTER);

            innerRelativeLayout.addView(verificationTextView);


            relativeLayout.addView(innerRelativeLayout);
        } else if (etat.equals("rejected")) {
            RelativeLayout innerRelativeLayout = new RelativeLayout(this);
            RelativeLayout.LayoutParams innerLayoutParams = new RelativeLayout.LayoutParams(
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics())
            );
            innerLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            innerLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            innerLayoutParams.setMargins(
                    0,
                    0,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 27, getResources().getDisplayMetrics()),
                    0
            );
            innerRelativeLayout.setLayoutParams(innerLayoutParams);
            innerRelativeLayout.setBackgroundResource(R.drawable.background_canceld);
            innerRelativeLayout.setElevation(2);

            TextView verificationTextView = new TextView(this);
            RelativeLayout.LayoutParams verificationParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );

            verificationParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            verificationTextView.setLayoutParams(verificationParams);

            verificationTextView.setPadding(
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics())
            );

            verificationTextView.setText("Canceled");
            verificationTextView.setTextColor(ContextCompat.getColor(this, R.color.red));
            verificationTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            verificationTextView.setTypeface(null, Typeface.BOLD);
            verificationTextView.setGravity(Gravity.CENTER);

            innerRelativeLayout.addView(verificationTextView);


            relativeLayout.addView(innerRelativeLayout);
        } else if (etat.equals("resupplying")) {
            RelativeLayout innerRelativeLayout = new RelativeLayout(this);
            RelativeLayout.LayoutParams innerLayoutParams = new RelativeLayout.LayoutParams(
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics())
            );
            innerLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            innerLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            innerLayoutParams.setMargins(
                    0,
                    0,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 27, getResources().getDisplayMetrics()),
                    0
            );
            innerRelativeLayout.setLayoutParams(innerLayoutParams);
            innerRelativeLayout.setBackgroundResource(R.drawable.background_restock);
            innerRelativeLayout.setElevation(2);

            TextView verificationTextView = new TextView(this);
            RelativeLayout.LayoutParams verificationParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );

            verificationParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            verificationTextView.setLayoutParams(verificationParams);

            verificationTextView.setPadding(
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics())
            );

            verificationTextView.setText("In restocking");
            verificationTextView.setTextColor(ContextCompat.getColor(this, R.color.yellow));
            verificationTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            verificationTextView.setTypeface(null, Typeface.BOLD);
            verificationTextView.setGravity(Gravity.CENTER);

            innerRelativeLayout.addView(verificationTextView);


            relativeLayout.addView(innerRelativeLayout);
            
        } else if (etat.equals("resupplied")) {
            RelativeLayout innerRelativeLayout = new RelativeLayout(this);
            RelativeLayout.LayoutParams innerLayoutParams = new RelativeLayout.LayoutParams(
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics())
            );
            innerLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            innerLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            innerLayoutParams.setMargins(
                    0,
                    0,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 27, getResources().getDisplayMetrics()),
                    0
            );
            innerRelativeLayout.setLayoutParams(innerLayoutParams);
            innerRelativeLayout.setBackgroundResource(R.drawable.background_restocked);
            innerRelativeLayout.setElevation(2);

            TextView verificationTextView = new TextView(this);
            RelativeLayout.LayoutParams verificationParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );

            verificationParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            verificationTextView.setLayoutParams(verificationParams);

            verificationTextView.setPadding(
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics())
            );

            verificationTextView.setText("Restocked");
            verificationTextView.setTextColor(ContextCompat.getColor(this, R.color.green));
            verificationTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            verificationTextView.setTypeface(null, Typeface.BOLD);
            verificationTextView.setGravity(Gravity.CENTER);

            innerRelativeLayout.addView(verificationTextView);


            relativeLayout.addView(innerRelativeLayout);
            
        }


        return relativeLayout;
    }

    public void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", com.example.scanmed.HistoryActivity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocal(this, language);
    }

    public void setLocal(com.example.scanmed.HistoryActivity activity, String langCode) {
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