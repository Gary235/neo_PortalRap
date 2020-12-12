package com.example.neo_portalrap.Fragments.Usuario;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.neo_portalrap.R;


public class fragment_viewpager_usuario extends Fragment {


    public static final String ARG_OBJECT = "object";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mis_bases, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        int num_object = args.getInt(ARG_OBJECT);
        Log.d("VER" , "Object: " + num_object);

        if(num_object == 1){
            ((TextView) view.findViewById(R.id.text1))
                    .setText("Grabaciones");

        }
        else {
            ((TextView) view.findViewById(R.id.text1))
                    .setText("Mis Bases");

        }




    }
}