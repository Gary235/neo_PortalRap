package com.example.neo_portalrap.Adaptadores;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.neo_portalrap.Fragments.Usuario.fragment_viewpager_usuario;

public class PagerAdapterUsuario extends FragmentStatePagerAdapter {
    public PagerAdapterUsuario(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new fragment_viewpager_usuario();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(fragment_viewpager_usuario.ARG_OBJECT, i + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (position + 1 == 1) ? "Grabaciones" : "Mis Bases";
    }



}
