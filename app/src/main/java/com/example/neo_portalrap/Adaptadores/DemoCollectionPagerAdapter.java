package com.example.neo_portalrap.Adaptadores;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.neo_portalrap.Fragments.Usuario.fragment_viewpager;

public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
    public DemoCollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new fragment_viewpager();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(fragment_viewpager.ARG_OBJECT, i + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (position + 1 == 1) ? "Mis Grabaciones" : "Mis Bases";
    }



}
