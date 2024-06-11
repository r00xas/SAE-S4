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

import com.example.scanmed.databinding.FragmentPasswordModifiedBinding;

public class PasswordModifiedFragment extends Fragment {

    private FragmentPasswordModifiedBinding binding;
    public static PasswordModifiedFragment newInstance() {
        return new PasswordModifiedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPasswordModifiedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AccountSettingsModifyFragment account_settings = AccountSettingsModifyFragment.newInstance();
                fragmentTransaction.replace(R.id.fragment_container, account_settings);
                fragmentTransaction.commit();
            }
        });
        binding.buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AccountSettingsModifyFragment account_settings = AccountSettingsModifyFragment.newInstance(); //page Home
                fragmentTransaction.replace(R.id.fragment_container, account_settings);
                fragmentTransaction.commit();
            }
        });
    }
}