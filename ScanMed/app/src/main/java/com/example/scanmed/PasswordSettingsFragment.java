package com.example.scanmed;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scanmed.databinding.FragmentCreatePasswordBinding;
import com.example.scanmed.databinding.FragmentPasswordSettingsBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PasswordSettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PasswordSettingsFragment extends Fragment {

    FragmentPasswordSettingsBinding binding;
    public static PasswordSettingsFragment newInstance(String param1, String param2) {
        PasswordSettingsFragment fragment = new PasswordSettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPasswordSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        binding.buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragment_manager = getParentFragmentManager();
                FragmentTransaction fragment_transaction = fragment_manager.beginTransaction();
                PasswordModifiedFragment password_modified_fragment = new PasswordModifiedFragment();
                fragment_transaction.replace(R.id.main, password_modified_fragment);
                fragment_transaction.addToBackStack(null);
                fragment_transaction.commit();
            }
        });
    }
}