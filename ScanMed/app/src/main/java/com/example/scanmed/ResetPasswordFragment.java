package com.example.scanmed;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scanmed.databinding.FragmentResetPasswordBinding;

public class ResetPasswordFragment extends Fragment {

    FragmentResetPasswordBinding binding;

    public static ResetPasswordFragment newInstance() {
        ResetPasswordFragment fragment = new ResetPasswordFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        binding.buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start a fragment transaction to replace LoginFragment with ResetPasswordFragment
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new CreatePasswordFragment())
                        .addToBackStack(null) // Add transaction to back stack to enable back navigation
                        .commit();
            }
        });

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start a fragment transaction to replace LoginFragment with ResetPasswordFragment
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new LoginFragment())
                        .addToBackStack(null) // Add transaction to back stack to enable back navigation
                        .commit();
            }
        });
    }
}