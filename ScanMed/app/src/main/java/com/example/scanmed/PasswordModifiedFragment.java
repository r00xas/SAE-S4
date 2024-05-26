package com.example.scanmed;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scanmed.databinding.FragmentPasswordModifiedBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PasswordModifiedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PasswordModifiedFragment extends Fragment {

        FragmentPasswordModifiedBinding binding;
        public static com.example.scanmed.PasswordChangedFragment newInstance(String param1, String param2) {
            com.example.scanmed.PasswordChangedFragment fragment = new com.example.scanmed.PasswordChangedFragment();
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            binding = FragmentPasswordModifiedBinding.inflate(inflater, container, false);
            return binding.getRoot();
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState){
            super.onViewCreated(view, savedInstanceState);
            binding.buttonBackHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragment_manager = getParentFragmentManager();
                    FragmentTransaction fragment_transaction = fragment_manager.beginTransaction();
                    HomepageFrag homepage_fragment = new HomepageFrag();
                    fragment_transaction.replace(R.id.main, homepage_fragment);
                    fragment_transaction.addToBackStack(null);
                    fragment_transaction.commit();
                }
            });
        }
    }
