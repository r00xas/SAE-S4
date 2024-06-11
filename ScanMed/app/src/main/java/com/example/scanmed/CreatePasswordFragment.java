package com.example.scanmed;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.scanmed.databinding.FragmentCreatePasswordBinding;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreatePasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreatePasswordFragment extends Fragment {

    FragmentCreatePasswordBinding binding;
    public static CreatePasswordFragment newInstance() {
        CreatePasswordFragment fragment = new CreatePasswordFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCreatePasswordBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.TextPassword1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.TextPassword2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.seePassword1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePasswordVisibility(binding.TextPassword1);
            }
        });

        binding.seePassword2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePasswordVisibility(binding.TextPassword2);
            }
        });

        binding.buttonAskReset.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                String newPwd = binding.TextPassword1.getText().toString();
                String confirmationpwd = binding.TextPassword2.getText().toString();
                if (!passwordRulesCheck(newPwd)) {
                    binding.LayoutPassword1.setBackground(getResources().getDrawable(R.drawable.input_text_style_error));
                } else if (!newPwd.equals(confirmationpwd)){
                    binding.LayoutPassword2.setBackground(getResources().getDrawable(R.drawable.input_text_style_error));
                } else {
                    Log.i("API", "connexion");
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    PasswordChangedFragment password_settings = PasswordChangedFragment.newInstance(); // go to home
                    fragmentTransaction.replace(R.id.fragment_container, password_settings);
                    fragmentTransaction.commit();
                }
            }
        });
    }
    private boolean checkGoodPassword(String pwd){
        SharedPreferences sharedPref = getActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        Log.i("API", sharedPref.getString("password", "none"));
        Log.i("API", pwd);
        Log.i("API", "Même mot de passe" + pwd.equals(sharedPref.getString("password", "none")));
        return pwd.equals(sharedPref.getString("password", "none"));
    }
    private boolean passwordRulesCheck(String pwd){
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,64}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(pwd).matches();
    }
    private void togglePasswordVisibility(EditText editText) {
        int inputType = editText.getInputType();
        if (inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
            //imageButton.setImageResource(R.drawable.ic_visibility);
        } else {
            inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
            //imageButton.setImageResource(R.drawable.ic_visibility_off);
        }
        editText.setInputType(inputType);
        editText.setSelection(editText.getText().length());
    }
}