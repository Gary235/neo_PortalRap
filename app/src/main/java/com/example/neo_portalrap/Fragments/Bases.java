package com.example.neo_portalrap.Fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.neo_portalrap.Adaptadores.AdaptadorRecycleView;
import com.example.neo_portalrap.Clases.Base;
import com.example.neo_portalrap.MainActivity;
import com.example.neo_portalrap.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;


import java.io.IOException;
import java.util.ArrayList;


public class Bases extends Fragment {

    androidx.appcompat.widget.Toolbar toolbar;
    private RecyclerView recyclerView;
    private AdaptadorRecycleView mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<Base> arrBases = new ArrayList<>();

    FirebaseFirestore db;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.bases_menu, menu);
        //FUNCION BUSQUEDA DE BEATS

        MenuItem searchItem = menu.findItem(R.id.buscar_bases);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Ej: Abandoned");

        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.filter(query);
                mAdapter.notifyDataSetChanged();

                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.filter(newText);
                mAdapter.notifyDataSetChanged();


                return true;
            }
        });


        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.toHome(true);
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
    public void onStart() {
        super.onStart();

        db = FirebaseFirestore.getInstance();
        obtenerListaBases();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        boolean train = args.getBoolean("train");

        View v = inflater.inflate(R.layout.fragment_bases, container, false);


        toolbar = v.findViewById(R.id.toolbar_bases);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);


        recyclerView = (RecyclerView) v.findViewById(R.id.recy_bases);
        recyclerView.setHasFixedSize(true);

        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setOnClickListener(a -> {
               /* if (encontrarBeat(mDataset.get(position).getUrl())){
                    //reproducir desde local
                    if(counterReproduccion == 0){
                        String path = Environment.getExternalStorageDirectory().toString() +
                                "/neo portal rap/bases/" +
                                mDataset.get(position).getUrl();

                        try {
                            mediaPlayer.setDataSource(path);
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        counterReproduccion++;
                    }
                    else if (!mediaPlayer.isPlaying()){
                        mediaPlayer.pause();
                    } else {
                        mediaPlayer.start();
                    }
                }
                else {
                    if(isNetworkConnected){
                        //reproducir online

                        if(counterReproduccion == 0){
                            StorageReference storageRef = mStorage.child("info_bases/bases/" + mDataset.get(position).getUrl());
                            storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                                try {
                                    // Download url of file
                                    final String url = uri.toString();
                                    mediaPlayer.setDataSource(url);
                                    // wait for media player to get prepare
                                    mediaPlayer.setOnPreparedListener(mp -> mp.start());
                                    mediaPlayer.prepareAsync();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }).addOnFailureListener(e -> Log.i("TAG", e.getMessage()));

                            counterReproduccion++;
                        }
                        else if (!mediaPlayer.isPlaying()){
                            mediaPlayer.pause();
                        } else {
                            mediaPlayer.start();
                        }

                    }

                    else {
                        Toast.makeText(miContexto, "Error de red", Toast.LENGTH_LONG).show();
                    }
                }*/
            });



        if(train){
            toolbar.setNavigationIcon(R.drawable.ic_cruz_negra);
            toolbar.setElevation(0);


        }
        else {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    if(dy > 0){
                        MainActivity.extFAB.hide();
                        MainActivity.bottom.setVisibility(View.GONE);
                    } else{
                        MainActivity.extFAB.show();
                        MainActivity.bottom.setVisibility(View.VISIBLE);
                    }

                    super.onScrolled(recyclerView, dx, dy);
                }
            });

        }

        return v;
    }


    private void obtenerListaBases() {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Cargando Bases");
        progressDialog.show();

        db.collection("Bases")
                .orderBy("nombre")
                .addSnapshotListener((snapshots, e) -> {
                    arrBases.clear();
                    recyclerView.setAdapter(null);
                    assert snapshots != null;
                    for (DocumentSnapshot document : snapshots) {
                        Base beat = document.toObject(Base.class);
                        assert beat != null;
                        beat.setId(document.getId());
                        arrBases.add(beat);
                    }

                    mAdapter = new AdaptadorRecycleView(arrBases, getActivity(), "bases");
                    recyclerView.setAdapter(mAdapter);
                    progressDialog.dismiss();

                });
    }

}