package com.example.neo_portalrap.Fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import com.example.neo_portalrap.R;


public class Home extends Fragment {

    Toolbar toolbar;



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.home_ayuda:
                AlertDialog.Builder mensaje;
                mensaje = new AlertDialog.Builder(getActivity());
                mensaje.setTitle("Home");
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

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        toolbar = v.findViewById(R.id.toolbar_home);
        setHasOptionsMenu(true);

        toolbar.setTitle("Hola, Gary");

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);






        return v;
    }





}