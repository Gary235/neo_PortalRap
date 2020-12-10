package com.example.neo_portalrap.Fragments;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.example.neo_portalrap.R;
import com.google.android.material.tabs.TabLayout;


public class Usuario extends Fragment  {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_usuario, container, false);

        viewPager = v.findViewById(R.id.viewpager_usuario);



        return v;
    }


}