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
                FragmentManager fragment_manager = getParentFragmentManager();
                FragmentTransaction fragment_transaction = fragment_manager.beginTransaction();
                CreatePasswordFragment create_password_fragment = new CreatePasswordFragment();
                fragment_transaction.replace(R.id.main, create_password_fragment);
                fragment_transaction.addToBackStack(null);
                fragment_transaction.commit();
            }
        });

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragment_manager = getParentFragmentManager();
                FragmentTransaction fragment_transaction = fragment_manager.beginTransaction();
                HomepageFrag homepage_frag = new HomepageFrag();
                fragment_transaction.replace(R.id.main, homepage_frag);
                fragment_transaction.addToBackStack(null);
                fragment_transaction.commit();
            }
        });
    }
}