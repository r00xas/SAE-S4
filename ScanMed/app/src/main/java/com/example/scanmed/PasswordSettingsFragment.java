package com.example.scanmed;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.scanmed.R;
import com.example.scanmed.databinding.FragmentPasswordSettingsBinding;

import org.json.JSONException;

import java.io.IOException;
import java.util.regex.Pattern;

public class PasswordSettingsFragment extends Fragment {

    private FragmentPasswordSettingsBinding binding;

    public static PasswordSettingsFragment newInstance() {
        return new PasswordSettingsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPasswordSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String currentPwd = binding.TextPassword1.getText().toString();
        String newPwd = binding.TextPassword2.getText().toString();
        String confirmationpwd = binding.TextPassword3.getText().toString();
        binding.imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AccountSettingsModifyFragment account_settings = AccountSettingsModifyFragment.newInstance();
                fragmentTransaction.replace(R.id.fragment_container, account_settings);
                fragmentTransaction.commit();
            }
        });

        binding.seePassword1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePasswordVisibility(binding.TextPassword1, binding.seePassword1);
            }
        });

        binding.seePassword2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePasswordVisibility(binding.TextPassword2, binding.seePassword2);
            }
        });

        binding.seePassword3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePasswordVisibility(binding.TextPassword3, binding.seePassword3);
            }
        });

        binding.buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                String currentPwd = binding.TextPassword1.getText().toString();
                String newPwd = binding.TextPassword2.getText().toString();
                String confirmationpwd = binding.TextPassword3.getText().toString();
                if (!checkGoodPassword(currentPwd)) {
                    binding.LayoutPassword1.setBackground(getResources().getDrawable(R.drawable.input_text_style_error));
                } else if (!passwordRulesCheck(newPwd)) {
                    binding.LayoutPassword2.setBackground(getResources().getDrawable(R.drawable.input_text_style_error));
                } else if (!newPwd.equals(confirmationpwd)) {
                    binding.LayoutPassword3.setBackground(getResources().getDrawable(R.drawable.input_text_style_error));
                } else {

                }
            }
        });
    }

    private boolean checkGoodPassword(String pwd) {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("my_preferences", MODE_PRIVATE);
        Log.i("API", sharedPref.getString("password", "none"));
        Log.i("API", pwd);
        Log.i("API", "Même mot de passe" + pwd.equals(sharedPref.getString("password", "none")));
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
}