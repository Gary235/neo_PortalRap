package com.example.neo_portalrap.Fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.neo_portalrap.Adaptadores.AdaptadorRecycleView;
import com.example.neo_portalrap.Clases.Base;
import com.example.neo_portalrap.MainActivity;
import com.example.neo_portalrap.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class Bases extends Fragment {

    Toolbar toolbar;
    ImageView img_paso4;
    ImageButton btnSiguiente, btnAnterior;
    TextView txt_paso4;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter searchAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<Base> arrBases = new ArrayList<>();
    ArrayList<Base> arrBusqueda = new ArrayList<>();

    FirebaseFirestore db;

    int textlength = 0;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.bases_menu, menu);
        //FUNCION BUSQUEDA DE BEATS

        final MenuItem searchItem = menu.findItem(R.id.buscar_bases);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Ej: Abandoned");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                textlength = newText.length();
                arrBusqueda.clear();
                recyclerView.setAdapter(null);
                for (int i = 0; i < arrBases.size(); i++) {
                    if (textlength <= arrBases.get(i).getNombre().length()) {
                        if (arrBases.get(i).getNombre().toLowerCase().contains(newText.toString().toLowerCase()) || arrBases.get(i).getArtista().toLowerCase().contains(newText.toLowerCase())) {
                            arrBusqueda.add(arrBases.get(i));
                        }
                    }
                }
                searchAdapter = new AdaptadorRecycleView(arrBusqueda,getActivity());
                recyclerView.setAdapter(searchAdapter);
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


        toolbar= v.findViewById(R.id.toolbar_bases);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        btnAnterior = v.findViewById(R.id.btn_anterior_bases);
        btnSiguiente = v.findViewById(R.id.btn_siguiente_bases);
        img_paso4 = v.findViewById(R.id.img_paso4);
        txt_paso4 = v.findViewById(R.id.txt_paso4);

        recyclerView = (RecyclerView) v.findViewById(R.id.recy_bases);
        recyclerView.setHasFixedSize(true);

        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        if(train){
            toolbar.setNavigationIcon(R.drawable.ic_cruz_negra);
            toolbar.setElevation(0);

            btnAnterior.setOnClickListener(a -> {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.toDuracion();

            });
            btnSiguiente.setOnClickListener(a -> {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.toCompletado();
            });

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    if(dy > 0){
                        btnAnterior.setVisibility(View.GONE);
                        btnSiguiente.setVisibility(View.GONE);
                    } else{
                        btnAnterior.setVisibility(View.VISIBLE);
                        btnSiguiente.setVisibility(View.VISIBLE);

                    }

                    super.onScrolled(recyclerView, dx, dy);
                }
            });
        }
        else {
            btnSiguiente.setVisibility(View.GONE);
            btnAnterior.setVisibility(View.GONE);
            txt_paso4.setVisibility(View.GONE);
            img_paso4.setVisibility(View.GONE);

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

                    progressDialog.dismiss();
                    mAdapter = new AdaptadorRecycleView(arrBases, getActivity());
                    recyclerView.setAdapter(mAdapter);

                });
    }

}