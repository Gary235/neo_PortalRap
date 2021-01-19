package com.example.neo_portalrap.Fragments.Usuario;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.neo_portalrap.R;


public class fragment_viewpager_fav extends Fragment {


    public static final String ARG_OBJECT = "object";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recycler, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recy_bases_grab);

        recyclerView.setHasFixedSize(true);



        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        int num_object = args.getInt(ARG_OBJECT);
        Log.d("VER" , "Object: " + num_object);


        if(num_object == 1){
            //bases fav / SOLO Favoritas
        }
        else if(num_object == 2){
            //grabaciones fav / SOLO Favoritas
        }
        else {
            //mis bases fav / SOLO Favoritas
        }





    }
}