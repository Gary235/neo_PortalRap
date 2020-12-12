package com.example.neo_portalrap.Fragments.Usuario;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.neo_portalrap.Adaptadores.PagerAdapterFavoritos;
import com.example.neo_portalrap.Adaptadores.PagerAdapterUsuario;
import com.example.neo_portalrap.MainActivity;
import com.example.neo_portalrap.R;
import com.google.android.material.tabs.TabLayout;


public class Favoritos extends Fragment {

    ViewPager viewPager;
    TabLayout tabLayout;
    PagerAdapterFavoritos pagerAdapterFavoritos;
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
                mainActivity.toUsuario();
                break;
            case R.id.home_ayuda:
                AlertDialog.Builder mensaje;
                mensaje = new AlertDialog.Builder(getActivity());
                mensaje.setTitle("Favoritos");
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

        View v = inflater.inflate(R.layout.fragment_favoritos, container, false);
        viewPager = v.findViewById(R.id.viewpager_fav);
        tabLayout= v.findViewById(R.id.tab_layout_fav);
        pagerAdapterFavoritos = new PagerAdapterFavoritos( ((AppCompatActivity)getActivity()).getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapterFavoritos);
        tabLayout.setupWithViewPager(viewPager);

        int[] tabIcons = {
                R.drawable.ic_bases,
                R.drawable.ic_grabaciones,
                R.drawable.ic_misbeats
        };

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);

        toolbar = v.findViewById(R.id.toolbar_fav);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return v;
    }
}