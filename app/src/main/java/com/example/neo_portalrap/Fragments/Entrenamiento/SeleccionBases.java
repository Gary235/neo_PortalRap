package com.example.neo_portalrap.Fragments.Entrenamiento;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.neo_portalrap.Adaptadores.AdapterSelBases;
import com.example.neo_portalrap.Clases.Base;
import com.example.neo_portalrap.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class SeleccionBases extends Fragment {

    ArrayList <Base> arrayList;
    private RecyclerView recyclerView;
    private AdapterSelBases mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    FirebaseFirestore db;


    @Override
    public void onStart() {
        super.onStart();

        db = FirebaseFirestore.getInstance();
        obtenerListaBases();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_seleccion_bases, container, false);

        arrayList = new ArrayList<>();

        recyclerView = (RecyclerView) v.findViewById(R.id.recyselbases);
        recyclerView.setHasFixedSize(true);
        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        return v;
    }

    private void obtenerListaBases() {


        db.collection("Bases")
                .orderBy("nombre")
                .addSnapshotListener((snapshots, e) -> {
                    arrayList.clear();
                    recyclerView.setAdapter(null);
                    assert snapshots != null;
                    for (DocumentSnapshot document : snapshots) {
                        Base beat = document.toObject(Base.class);
                        assert beat != null;
                        beat.setId(document.getId());
                        arrayList.add(beat);
                    }

                    mAdapter = new AdapterSelBases(getActivity(), arrayList);
                    recyclerView.setAdapter(mAdapter);
                });
    }

}