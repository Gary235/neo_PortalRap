package com.example.neo_portalrap.Fragments.Entrenamiento;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.neo_portalrap.MainActivity;
import com.example.neo_portalrap.R;


public class Modo extends Fragment {

    androidx.appcompat.widget.Toolbar toolbar;
    ImageButton btnSiguiente;

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
                mainActivity.toHome();
                break;
            case R.id.home_ayuda:
                AlertDialog.Builder mensaje;
                mensaje = new AlertDialog.Builder(getActivity());
                mensaje.setTitle("Modo");
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

        View v = inflater.inflate(R.layout.fragment_modo, container, false);

        toolbar = v.findViewById(R.id.toolbar_modo);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);


        btnSiguiente = v.findViewById(R.id.btn_siguiente_modo);

        btnSiguiente.setOnClickListener(s ->  {

            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.toFrecuencia();

        });


        return v;
    }
}