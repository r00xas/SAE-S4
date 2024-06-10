package com.example.scanmed;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scanmed.databinding.FragmentPasswordChangedBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PasswordChangedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PasswordChangedFragment extends Fragment {
    FragmentPasswordChangedBinding binding;
    public static PasswordChangedFragment newInstance(String param1, String param2) {
        PasswordChangedFragment fragment = new PasswordChangedFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPasswordChangedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        binding.buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomepageActivity.class);
                startActivity(intent);
            }
        });
    }
}