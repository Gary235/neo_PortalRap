package com.example.neo_portalrap.Fragments.Usuario;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.neo_portalrap.Adaptadores.DemoCollectionPagerAdapter;
import com.example.neo_portalrap.R;
import com.google.android.material.tabs.TabLayout;


public class Usuario extends Fragment  {

    ViewPager viewPager;
    TabLayout tabLayout;
    DemoCollectionPagerAdapter demoCollectionPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_usuario, container, false);

        viewPager = v.findViewById(R.id.viewpager_usuario);
        tabLayout= v.findViewById(R.id.tab_layout_usuario);
        demoCollectionPagerAdapter = new DemoCollectionPagerAdapter( ((AppCompatActivity)getActivity()).getSupportFragmentManager());

        viewPager.setAdapter(demoCollectionPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        int[] tabIcons = {
                R.drawable.ic_grabaciones,
                R.drawable.ic_misbeats
        };
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);


        return v;
    }


}