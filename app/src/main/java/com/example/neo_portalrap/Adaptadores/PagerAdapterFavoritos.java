package com.example.neo_portalrap.Adaptadores;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.neo_portalrap.Fragments.Usuario.fragment_viewpager_fav;
import com.example.neo_portalrap.Fragments.Usuario.fragment_viewpager_usuario;

public class PagerAdapterFavoritos extends FragmentStatePagerAdapter {
    public PagerAdapterFavoritos(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new fragment_viewpager_fav();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(fragment_viewpager_fav.ARG_OBJECT, i + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "Bases";
            case 1:
                return "Grabaciones";
            case 2:
                return "Mis Bases";
            default:
                return "";
        }


    }



}
