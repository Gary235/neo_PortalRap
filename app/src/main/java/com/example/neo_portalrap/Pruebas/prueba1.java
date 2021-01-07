package com.example.neo_portalrap.Pruebas;


import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.neo_portalrap.Fragments.Entrenamiento.Completado;
import com.example.neo_portalrap.Fragments.Entrenamiento.Duracion;
import com.example.neo_portalrap.Fragments.Entrenamiento.Frecuencia;
import com.example.neo_portalrap.Fragments.Entrenamiento.Modo;
import com.example.neo_portalrap.R;
import com.google.android.material.tabs.TabLayout;


public class prueba1 extends android.app.Fragment {

    ViewPager viewPager;

    private static final int NUM_PAGES = 5;
    private PagerAdapter pagerAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_prueba1, container, false);
        viewPager = v.findViewById(R.id.viewpagerprueba);

        pagerAdapter = new ScreenSlidePagerAdapter(((AppCompatActivity)getActivity()).getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tab_layoutprueba);
        tabLayout.setupWithViewPager(viewPager, true);


        return v;
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    return new Modo();
                case 1:
                    return new Frecuencia();
                case 2:
                    return new Duracion();
                case 3:
                    return new Completado();
                case 4:
                    return new Completado();

                default:
                    return null;
            }


        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

}