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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.neo_portalrap.MainActivity;
import com.example.neo_portalrap.R;


public class Bases extends Fragment {

    Toolbar toolbar;
    ImageView img_paso4;
    ImageButton btnSiguiente, btnAnterior;
    TextView txt_paso4;


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
            case android.R.id.home:
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.toHome();
                break;
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
        Bundle args = getArguments();
        boolean train = args.getBoolean("train");

        View v = inflater.inflate(R.layout.fragment_bases, container, false);
        toolbar= v.findViewById(R.id.toolbar_bases);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        btnAnterior = v.findViewById(R.id.btn_anterior_bases);
        btnSiguiente = v.findViewById(R.id.btn_siguiente_bases);
        img_paso4 = v.findViewById(R.id.img_paso4);
        txt_paso4 = v.findViewById(R.id.txt_paso4);

        if(train){
            toolbar.setNavigationIcon(R.drawable.ic_cruz_negra);

            btnAnterior.setOnClickListener(a -> {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.toDuracion();

            });
            btnSiguiente.setOnClickListener(a -> {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.toCompletado();
            });

        }   else {
            btnSiguiente.setVisibility(View.GONE);
            btnAnterior.setVisibility(View.GONE);
            txt_paso4.setVisibility(View.GONE);
            img_paso4.setVisibility(View.GONE);

        }

        return v;
    }
}