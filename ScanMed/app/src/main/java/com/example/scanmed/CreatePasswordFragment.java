package com.example.scanmed;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scanmed.databinding.FragmentCreatePasswordBinding;

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
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        binding.buttonAskReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragment_manager = getParentFragmentManager();
                FragmentTransaction fragment_transaction = fragment_manager.beginTransaction();
                PasswordChangedFragment password_changed_fragment = new PasswordChangedFragment();
                fragment_transaction.replace(R.id.main, password_changed_fragment);
                fragment_transaction.addToBackStack(null);
                fragment_transaction.commit();
            }
        });
    }
}