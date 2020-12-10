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
import androidx.appcompat.widget.Toolbar;

import com.example.neo_portalrap.R;


public class Bases extends Fragment {

    Toolbar toolbar;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.bases_menu, menu);

        //FUNCION BUSQUEDA DE BEATS

        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.ayuda_bases:
                AlertDialog.Builder mensaje;
                mensaje = new AlertDialog.Builder(getActivity());
                mensaje.setTitle("Bases");
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
        View v = inflater.inflate(R.layout.fragment_bases, container, false);
        toolbar= v.findViewById(R.id.toolbar_bases);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);


        return v;
    }
}