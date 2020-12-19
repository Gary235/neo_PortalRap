package com.example.neo_portalrap.Fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.neo_portalrap.Adaptadores.AdaptadorRecycleView;
import com.example.neo_portalrap.MainActivity;
import com.example.neo_portalrap.R;

import java.util.ArrayList;


public class Bases extends Fragment {

    Toolbar toolbar;
    ImageView img_paso4;
    ImageButton btnSiguiente, btnAnterior;
    TextView txt_paso4;

    ListView listaBases;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<Integer> arrayList = new ArrayList<>();
    ArrayAdapter<Integer> arrayAdapter;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.bases_menu, menu);
        //FUNCION BUSQUEDA DE BEATS

        SearchManager searchManager =
                (SearchManager) ((AppCompatActivity)getActivity()).getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.buscar_bases).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(((AppCompatActivity)getActivity()).getComponentName()));
        searchView.setQueryHint("Ej: demon");




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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        boolean train = args.getBoolean("train");

        View v = inflater.inflate(R.layout.fragment_bases, container, false);

        final Handler handler = new Handler();
        handler.postDelayed(() -> {

            MainActivity.extFAB.shrink();
        }, 2000);



        toolbar= v.findViewById(R.id.toolbar_bases);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        btnAnterior = v.findViewById(R.id.btn_anterior_bases);
        btnSiguiente = v.findViewById(R.id.btn_siguiente_bases);
        img_paso4 = v.findViewById(R.id.img_paso4);
        txt_paso4 = v.findViewById(R.id.txt_paso4);

        recyclerView = (RecyclerView) v.findViewById(R.id.recy_bases);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
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


        for(int i = 0 ; i < 58; i++){
            arrayList.add(i);
        }


        mAdapter = new AdaptadorRecycleView(arrayList);
        recyclerView.setAdapter(mAdapter);





        return v;
    }
}