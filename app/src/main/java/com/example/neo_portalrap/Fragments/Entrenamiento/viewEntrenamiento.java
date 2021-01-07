package com.example.neo_portalrap.Fragments.Entrenamiento;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.neo_portalrap.MainActivity;
import com.example.neo_portalrap.Pruebas.prueba1;
import com.example.neo_portalrap.R;
import com.google.android.material.tabs.TabLayout;


public class viewEntrenamiento extends android.app.Fragment {
    ViewPager viewPager;

    private static final int NUM_PAGES = 5;
    private PagerAdapter pagerAdapter;
    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.ayuda_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.toHome(true);
                break;
            case R.id.home_ayuda:
                AlertDialog.Builder mensaje;
                mensaje = new AlertDialog.Builder(getActivity());
                mensaje.setTitle("Personalizar Entrenamiento");
                mensaje.setMessage("hola");
                mensaje.setPositiveButton("Ok",null);
                mensaje.create();
                mensaje.show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle args = getArguments();
        boolean desdehome = false;

        View v = inflater.inflate(R.layout.fragment_entrenamiento, container, false);

        v.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (desdehome){
                    v.removeOnLayoutChangeListener(this);
                    int cx = (int) MainActivity.extFAB.getX();
                    int cy = (int)MainActivity.extFAB.getY();
                    int width = v.getWidth();
                    int height = v.getHeight();


                    float finalRadius = Math.max(width, height) / 2 + Math.max(width - cx, height - cy);
                    Animator anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, finalRadius);
                    //anim.setDuration(300);
                    anim.start();
                }
            }
        });


        toolbar = v.findViewById(R.id.toolbar_modo);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);



        viewPager = v.findViewById(R.id.viewpagerentrenamiento);
        pagerAdapter = new ScreenSlidePagerAdapter(((AppCompatActivity)getActivity()).getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tab_layoutentrenamiento);
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