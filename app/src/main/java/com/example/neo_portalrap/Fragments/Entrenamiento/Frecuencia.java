package com.example.neo_portalrap.Fragments.Entrenamiento;

import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.neo_portalrap.MainActivity;
import com.example.neo_portalrap.R;


public class Frecuencia extends Fragment {


    Button btn2s, btn5s, btn10s, btn15s;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_frecuencia, container, false);

        btn2s = v.findViewById(R.id.btn2s);
        btn5s = v.findViewById(R.id.btn5s);
        btn10s = v.findViewById(R.id.btn10s);
        btn15s = v.findViewById(R.id.btn15s);


        switch (MainActivity.frecuencia){
            case 0:
                btn2s.setBackgroundResource(R.drawable.redondeado_violeta);
                break;
            case 1:
                btn5s.setBackgroundResource(R.drawable.redondeado_violeta);
                break;
            case 2:
                btn10s.setBackgroundResource(R.drawable.redondeado_violeta);
                break;
            case 3:
                btn15s.setBackgroundResource(R.drawable.redondeado_violeta);
                break;
        }



        btn2s.setOnClickListener(a -> {
            MainActivity.frecuencia = 0;

            btn2s.setBackgroundResource(R.drawable.redondeado_violeta);
            btn5s.setBackgroundResource(R.drawable.redondeado_gris);
            btn10s.setBackgroundResource(R.drawable.redondeado_gris);
            btn15s.setBackgroundResource(R.drawable.redondeado_gris);
        });
        btn5s.setOnClickListener(a -> {
            MainActivity.frecuencia = 1;

            btn2s.setBackgroundResource(R.drawable.redondeado_gris);
            btn5s.setBackgroundResource(R.drawable.redondeado_violeta);
            btn10s.setBackgroundResource(R.drawable.redondeado_gris);
            btn15s.setBackgroundResource(R.drawable.redondeado_gris);

        });
        btn10s.setOnClickListener(a -> {
            MainActivity.frecuencia = 2;

            btn2s.setBackgroundResource(R.drawable.redondeado_gris);
            btn5s.setBackgroundResource(R.drawable.redondeado_gris);
            btn10s.setBackgroundResource(R.drawable.redondeado_violeta);
            btn15s.setBackgroundResource(R.drawable.redondeado_gris);

        });
        btn15s.setOnClickListener(a -> {
            MainActivity.frecuencia = 3;

            btn2s.setBackgroundResource(R.drawable.redondeado_gris);
            btn5s.setBackgroundResource(R.drawable.redondeado_gris);
            btn10s.setBackgroundResource(R.drawable.redondeado_gris);
            btn15s.setBackgroundResource(R.drawable.redondeado_violeta);

        });


        return v;
    }
}