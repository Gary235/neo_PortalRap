package com.example.neo_portalrap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.neo_portalrap.Fragments.Bases;
import com.example.neo_portalrap.Fragments.Entrenamiento.Completado;
import com.example.neo_portalrap.Fragments.Entrenamiento.Duracion;
import com.example.neo_portalrap.Fragments.Entrenamiento.Frecuencia;
import com.example.neo_portalrap.Fragments.Home;
import com.example.neo_portalrap.Fragments.Entrenamiento.Modo;
import com.example.neo_portalrap.Fragments.Usuario.Usuario;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;
    FragmentManager adminFragment;
    FragmentTransaction transaccionFragment;
    Fragment FragGlobal;

    public static BottomNavigationView bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();
        adminFragment = getFragmentManager();

        bottom = findViewById(R.id.bottomnavigation);
        bottom.setOnNavigationItemSelectedListener(listenernav);


        toHome();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener listenernav=  new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("ResourceType")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragseleccionado=null;

            switch (item.getItemId()){
                case R.id.home:
                    toHome();

                    break;
                case R.id.bases:
                    toBases(false);

                    break;
                case R.id.usuario:
                    toUsuario();
                    break;
            }

            return true;
        }
    };


    public void toHome() {
        bottom.setVisibility(View.VISIBLE);
        FragGlobal = new Home();
        transaccionFragment=adminFragment.beginTransaction();
        transaccionFragment.replace(R.id.frameLayout, FragGlobal,null);
        transaccionFragment.addToBackStack(null);
        transaccionFragment.commit();
    }

    public void toUsuario() {
        FragGlobal = new Usuario();
        transaccionFragment=adminFragment.beginTransaction();
        transaccionFragment.replace(R.id.frameLayout, FragGlobal,null);
        transaccionFragment.addToBackStack(null);
        transaccionFragment.commit();
    }

    public void toBases(Boolean train) {
        FragGlobal = new Bases();
        Bundle args = new Bundle();
        args.putBoolean("train", train);
        FragGlobal.setArguments(args);

        transaccionFragment=adminFragment.beginTransaction();
        transaccionFragment.replace(R.id.frameLayout, FragGlobal,null);
        transaccionFragment.addToBackStack(null);
        transaccionFragment.commit();
    }

    public void toModo() {
        bottom.setVisibility(View.GONE);
        FragGlobal = new Modo();
        transaccionFragment=adminFragment.beginTransaction();
        transaccionFragment.replace(R.id.frameLayout, FragGlobal,null);
        transaccionFragment.addToBackStack(null);
        transaccionFragment.commit();
    }

    public void toFrecuencia() {
        FragGlobal = new Frecuencia();
        transaccionFragment=adminFragment.beginTransaction();
        transaccionFragment.replace(R.id.frameLayout, FragGlobal,null);
        transaccionFragment.addToBackStack(null);
        transaccionFragment.commit();
    }

    public void toDuracion() {
        FragGlobal = new Duracion();
        transaccionFragment=adminFragment.beginTransaction();
        transaccionFragment.replace(R.id.frameLayout, FragGlobal,null);
        transaccionFragment.addToBackStack(null);
        transaccionFragment.commit();
    }

    public void toCompletado() {
        FragGlobal = new Completado();
        transaccionFragment=adminFragment.beginTransaction();
        transaccionFragment.replace(R.id.frameLayout, FragGlobal,null);
        transaccionFragment.addToBackStack(null);
        transaccionFragment.commit();
    }


}