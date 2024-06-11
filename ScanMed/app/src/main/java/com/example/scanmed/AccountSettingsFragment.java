package com.example.scanmed;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scanmed.databinding.FragmentAccountSettingsBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountSettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountSettingsFragment extends Fragment {
    FragmentAccountSettingsBinding binding;
    public static AccountSettingsFragment newInstance() {
        return new AccountSettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAccountSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.backButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PasswordSettingsFragment password_settings = PasswordSettingsFragment.newInstance(); // go to home
                fragmentTransaction.replace(R.id.fragment_container, password_settings);
                fragmentTransaction.commit();
            }
        });
        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PasswordSettingsFragment password_settings = PasswordSettingsFragment.newInstance(); // go to edit profil picture
                fragmentTransaction.replace(R.id.fragment_container, password_settings);
                fragmentTransaction.commit();
            }
        });
        binding.Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AccountSettingsModifyFragment account_settings_modify = AccountSettingsModifyFragment.newInstance();
                fragmentTransaction.replace(R.id.fragment_container, account_settings_modify);
                fragmentTransaction.commit();
            }
        });
        binding.CGU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PasswordSettingsFragment password_settings = PasswordSettingsFragment.newInstance(); // go to CGU
                fragmentTransaction.replace(R.id.fragment_container, password_settings);
                fragmentTransaction.commit();
            }
        });
        binding.HelpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PasswordSettingsFragment password_settings = PasswordSettingsFragment.newInstance(); // go to Help Center
                fragmentTransaction.replace(R.id.fragment_container, password_settings);
                fragmentTransaction.commit();
            }
        });
        binding.buttonSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PasswordSettingsFragment password_settings = PasswordSettingsFragment.newInstance(); // go to Help Center
                fragmentTransaction.replace(R.id.fragment_container, password_settings);
                fragmentTransaction.commit();
            }
        });
    }
}