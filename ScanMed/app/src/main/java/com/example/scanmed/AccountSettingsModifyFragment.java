package com.example.scanmed;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.scanmed.databinding.FragmentAccountSettingsModifyBinding;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountSettingsModifyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountSettingsModifyFragment extends Fragment {

    private FragmentAccountSettingsModifyBinding binding;
    public static AccountSettingsModifyFragment newInstance() {
        return new AccountSettingsModifyFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences sharedPref = requireActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username", "toto");
        editor.putString("password", "Ab=1aaaa");
        editor.putString("mail", "e.launay512@gmail.com");
        editor.apply();
        binding = FragmentAccountSettingsModifyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sharedPref = getActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);

        binding.editUserText.setText(sharedPref.getString("username", "none"), TextView.BufferType.EDITABLE);
        binding.editMailText.setText(sharedPref.getString("mail", "none"), TextView.BufferType.EDITABLE);
        binding.editPasswordText.setText("tototototototo", TextView.BufferType.EDITABLE);
        binding.backButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AccountSettingsFragment accountSettings = AccountSettingsFragment.newInstance();
                fragmentTransaction.replace(R.id.fragment_container, accountSettings);
                fragmentTransaction.commit();
            }
        });
        binding.editPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PasswordSettingsFragment password_settings = PasswordSettingsFragment.newInstance();
                fragmentTransaction.replace(R.id.fragment_container, password_settings);
                fragmentTransaction.commit();
            }
        });

        binding.buttonReset.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                if (binding.editMailText.getText().toString().contains("@"))
                    save();
                else {
                    binding.editMailLayout.setBackground(getResources().getDrawable(R.drawable.input_text_style_error));
                }
            }
        });

    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void save(){
        SharedPreferences sharedPref = requireActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username", binding.editUserText.getText().toString());
        editor.putString("password", binding.editPasswordText.getText().toString());
        editor.putString("mail", binding.editMailText.getText().toString());
        editor.apply();
        binding.editMailLayout.setBackground(getResources().getDrawable(R.drawable.input_text_style));
    }
}