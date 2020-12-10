package com.example.neo_portalrap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.neo_portalrap.Fragments.Bases;
import com.example.neo_portalrap.Fragments.Home;
import com.example.neo_portalrap.Fragments.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

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
                    fragseleccionado = new Home();
                    /*FragBases.isActionMode = false;
                    FragBases.actionMode = null;
                    FragBases.UserSelection.clear();*/
                    break;
                case R.id.bases:
                    fragseleccionado = new Bases();
                    /*FragBases.isActionMode = false;
                    FragBases.actionMode = null;
                    FragBases.UserSelection.clear();

                    Bundle args = new Bundle();
                    args.putString("desdedur","no");
                    fragseleccionado.setArguments(args);*/
                    break;
                case R.id.usuario:
                    fragseleccionado = new Usuario();
                    /*FragBases.isActionMode = false;
                    FragBases.actionMode = null;
                    FragBases.UserSelection.clear();*/
                    break;
            }

            transaccionFragment=adminFragment.beginTransaction();
            transaccionFragment.replace(R.id.frameLayout, fragseleccionado,null);
            transaccionFragment.addToBackStack(null);
            transaccionFragment.commit();
            return true;
        }
    };


    public void toHome() {
        FragGlobal = new Home();
        transaccionFragment=adminFragment.beginTransaction();
        transaccionFragment.replace(R.id.frameLayout, FragGlobal,null);
        transaccionFragment.addToBackStack(null);
        transaccionFragment.commit();

    }



}