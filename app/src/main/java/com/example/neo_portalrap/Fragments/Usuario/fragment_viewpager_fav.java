package com.example.neo_portalrap.Fragments.Usuario;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.neo_portalrap.Adaptadores.AdaptadorRecycleView;
import com.example.neo_portalrap.R;

import java.util.ArrayList;


public class fragment_viewpager_fav extends Fragment {


    public static final String ARG_OBJECT = "object";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<Integer> arrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mis_bases, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recy_bases_grab);

        recyclerView.setHasFixedSize(true);



        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        int num_object = args.getInt(ARG_OBJECT);
        Log.d("VER" , "Object: " + num_object);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        if(num_object == 1){
            //bases fav / SOLO Favoritas
            for (int i = 0; i < 120; i++){
                arrayList.add(i);
            }
        }
        else if(num_object == 2){
            //grabaciones fav / SOLO Favoritas

            for (int i = 120; i > 0; i--){
                arrayList.add(i);
            }
        }
        else {
            //mis bases fav / SOLO Favoritas
            for (int i = 0; i < 120; i++){
                arrayList.add(i);
            }
        }


        mAdapter = new AdaptadorRecycleView(arrayList);
        recyclerView.setAdapter(mAdapter);


    }
}