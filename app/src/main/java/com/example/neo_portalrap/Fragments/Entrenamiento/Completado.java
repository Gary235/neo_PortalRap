package com.example.neo_portalrap.Fragments.Entrenamiento;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.neo_portalrap.MainActivity;
import com.example.neo_portalrap.R;


public class Completado extends Fragment {


    Button btnVolver, btnEntrenar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_completado, container, false);

        btnVolver = v.findViewById(R.id.btn_volver_completado);
        btnEntrenar = v.findViewById(R.id.btn_entrenar_completado);

        btnVolver.setOnClickListener(a -> {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.toBases(true);

        });
        btnEntrenar.setOnClickListener(a -> {


        });

        return v;
    }
}