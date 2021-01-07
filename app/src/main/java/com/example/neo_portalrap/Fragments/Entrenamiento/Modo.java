package com.example.neo_portalrap.Fragments.Entrenamiento;


import android.annotation.SuppressLint;
import android.graphics.Color;
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

        objetos.setOnClickListener(a -> {
            MainActivity.modo[0] = 1;
            MainActivity.modo[1] = 0;
            MainActivity.modo[2] = 0;

            objetos.setColorFilter(ContextCompat.getColor(getActivity(), R.color.teal_700), android.graphics.PorterDuff.Mode.MULTIPLY);
            palabras.setColorFilter(ContextCompat.getColor(getActivity(), R.color.grey), android.graphics.PorterDuff.Mode.MULTIPLY);
            aleatorio.setColorFilter(ContextCompat.getColor(getActivity(), R.color.grey), android.graphics.PorterDuff.Mode.MULTIPLY);

        });

        palabras.setOnClickListener(a -> {
            MainActivity.modo[0] = 0;
            MainActivity.modo[1] = 1;
            MainActivity.modo[2] = 0;

            objetos.setColorFilter(ContextCompat.getColor(getActivity(), R.color.grey), android.graphics.PorterDuff.Mode.MULTIPLY);
            palabras.setColorFilter(ContextCompat.getColor(getActivity(), R.color.violeta), android.graphics.PorterDuff.Mode.MULTIPLY);
            aleatorio.setColorFilter(ContextCompat.getColor(getActivity(), R.color.grey), android.graphics.PorterDuff.Mode.MULTIPLY);

        });

        aleatorio.setOnClickListener(a -> {
            MainActivity.modo[0] = 0;
            MainActivity.modo[1] = 0;
            MainActivity.modo[2] = 1;

            objetos.setColorFilter(ContextCompat.getColor(getActivity(), R.color.grey), android.graphics.PorterDuff.Mode.MULTIPLY);
            palabras.setColorFilter(ContextCompat.getColor(getActivity(), R.color.grey), android.graphics.PorterDuff.Mode.MULTIPLY);
            aleatorio.setColorFilter(ContextCompat.getColor(getActivity(), R.color.violeta), android.graphics.PorterDuff.Mode.MULTIPLY);
        });


        return v;
    }
}