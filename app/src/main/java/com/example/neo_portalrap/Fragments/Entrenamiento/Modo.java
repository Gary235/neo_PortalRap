package com.example.neo_portalrap.Fragments.Entrenamiento;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.neo_portalrap.MainActivity;
import com.example.neo_portalrap.R;


public class Modo extends Fragment {

    ImageView palabras, objetos, aleatorio;

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_modo, container, false);

        objetos = v.findViewById(R.id.imgobjetos);
        palabras = v.findViewById(R.id.imgpalabras);
        aleatorio = v.findViewById(R.id.imgaleatorio);


        switch (MainActivity.modo){
            case 0:
                objetos.setBackgroundResource(R.drawable.redondeado_violeta);
                break;
            case 1:
                palabras.setBackgroundResource(R.drawable.redondeado_violeta);
                break;
            case 2:
                aleatorio.setBackgroundResource(R.drawable.redondeado_violeta);
                break;
        }


        objetos.setOnClickListener(a -> {
            MainActivity.modo = 0;

            objetos.setBackgroundResource(R.drawable.redondeado_violeta);
            palabras.setBackgroundResource(R.drawable.redondeado_gris);
            aleatorio.setBackgroundResource(R.drawable.redondeado_gris);
        });
        palabras.setOnClickListener(a -> {
            MainActivity.modo = 1;

            objetos.setBackgroundResource(R.drawable.redondeado_gris);
            palabras.setBackgroundResource(R.drawable.redondeado_violeta);
            aleatorio.setBackgroundResource(R.drawable.redondeado_gris);

        });
        aleatorio.setOnClickListener(a -> {
            MainActivity.modo = 2;

            objetos.setBackgroundResource(R.drawable.redondeado_gris);
            palabras.setBackgroundResource(R.drawable.redondeado_gris);
            aleatorio.setBackgroundResource(R.drawable.redondeado_violeta);
        });


        return v;
    }
}