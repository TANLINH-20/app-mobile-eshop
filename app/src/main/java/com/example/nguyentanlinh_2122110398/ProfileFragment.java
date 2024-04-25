package com.example.nguyentanlinh_2122110398;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyentanlinh_2122110398.activities.WelcomeActivity;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public void logOut(View view) {
        startActivity(new Intent(getActivity(), WelcomeActivity.class));
    }
}