package com.example.neo_portalrap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;

import com.example.neo_portalrap.Fragments.Bases;
import com.example.neo_portalrap.Fragments.Entrenamiento.Completado;
import com.example.neo_portalrap.Fragments.Entrenamiento.Duracion;
import com.example.neo_portalrap.Fragments.Entrenamiento.Frecuencia;
import com.example.neo_portalrap.Fragments.Home;
import com.example.neo_portalrap.Fragments.Entrenamiento.Modo;
import com.example.neo_portalrap.Fragments.Usuario.EditarPerfil;
import com.example.neo_portalrap.Fragments.Usuario.Favoritos;
import com.example.neo_portalrap.Fragments.Usuario.Usuario;
import com.example.neo_portalrap.Pruebas.prueba1;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;
    FragmentManager adminFragment;
    FragmentTransaction transaccionFragment;
    Fragment FragGlobal;

    public static BottomNavigationView bottom;
    public static FloatingActionButton FAB;
    public static ExtendedFloatingActionButton extFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();
        adminFragment = getFragmentManager();

        bottom = findViewById(R.id.bottomnavigation);
        bottom.setOnNavigationItemSelectedListener(listenernav);

        extFAB = findViewById(R.id.extended_fab);
        setHomeFAB();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        toHome(false);
    }



    public void setUserFAB(){
        extFAB.setVisibility(View.VISIBLE);
        extFAB.setOnClickListener(a -> {

        });

    }
    public void setHomeFAB(){


        extFAB.setVisibility(View.VISIBLE);
        extFAB.setOnClickListener(a -> {
            toModo(true);
        });


    }
    public void setBasesFAB(){

        extFAB.setVisibility(View.VISIBLE);
        extFAB.setOnClickListener(a -> {
            toModo(true);
        });


    }
    int[] iconIntArray = {
            R.drawable.ic_grabar,
            R.drawable.ic_grabar,
            R.drawable.ic_plus
    };

    String[] fabtextarray = {
            "Entrenar",
            "Entrenar",
            "Subir Base"

    };

    protected void animateFab(final int position) {
        extFAB.clearAnimation();
        // Scale down animation
        ScaleAnimation shrink =  new ScaleAnimation(1f, 0.2f, 1f, 0.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        shrink.setDuration(150);     // animation duration in milliseconds
        shrink.setInterpolator(new DecelerateInterpolator());
        shrink.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Change FAB color and icon
                extFAB.setIconResource(iconIntArray[position]);
                extFAB.setText(fabtextarray[position]);

                switch (position){
                    case 0:
                        setHomeFAB();
                        break;
                    case 1:
                        setBasesFAB();
                        break;
                    case 2:
                        setUserFAB();
                        break;
                }

                // Scale up animation
                ScaleAnimation expand =  new ScaleAnimation(0.2f, 1f, 0.2f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                expand.setDuration(100);     // animation duration in milliseconds
                expand.setInterpolator(new AccelerateInterpolator());
                extFAB.startAnimation(expand);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        extFAB.startAnimation(shrink);
    }
    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener listenernav = item -> {

        switch (item.getItemId()){
            case R.id.home:
                toHome(false);
                animateFab(0);

                break;
            case R.id.bases:
                toBases(false);
                animateFab(1);

                break;
            case R.id.usuario:
                toUsuario();
                animateFab(2);
                break;
        }

        return true;
    };


    public void toPrueba() {
        //bottom.setVisibility(View.VISIBLE);
        //extFAB.setVisibility(View.VISIBLE);


        FragGlobal = new prueba1();
        transaccionFragment = adminFragment.beginTransaction();
        transaccionFragment.replace(R.id.frameLayout, FragGlobal,null);
        transaccionFragment.addToBackStack(null);
        transaccionFragment.commit();
    }

    public void toHome(boolean desdeentrenamiento) {
        bottom.setVisibility(View.VISIBLE);
        extFAB.setVisibility(View.VISIBLE);

        FragGlobal = new Home();
        Bundle args = new Bundle();
        args.putBoolean("desdeentrenamiento", desdeentrenamiento);
        FragGlobal.setArguments(args);

        extFAB.extend();

        transaccionFragment=adminFragment.beginTransaction();
        transaccionFragment.replace(R.id.frameLayout, FragGlobal,null);
        transaccionFragment.addToBackStack(null);
        transaccionFragment.commit();
    }

    public void toUsuario() {
        bottom.setVisibility(View.VISIBLE);
        extFAB.setVisibility(View.VISIBLE);

        extFAB.extend();

        FragGlobal = new Usuario();
        transaccionFragment=adminFragment.beginTransaction();
        transaccionFragment.replace(R.id.frameLayout, FragGlobal,null);
        transaccionFragment.addToBackStack(null);
        transaccionFragment.commit();
    }

    public void toFavoritos() {
        bottom.setVisibility(View.GONE);
        extFAB.setVisibility(View.GONE);

        FragGlobal = new Favoritos();
        transaccionFragment=adminFragment.beginTransaction();
        transaccionFragment.replace(R.id.frameLayout, FragGlobal,null);
        transaccionFragment.addToBackStack(null);
        transaccionFragment.commit();
    }

    public void toEditarPerfil() {
        bottom.setVisibility(View.GONE);
        extFAB.setVisibility(View.GONE);

        FragGlobal = new EditarPerfil();
        transaccionFragment=adminFragment.beginTransaction();
        transaccionFragment.replace(R.id.frameLayout, FragGlobal,null);
        transaccionFragment.addToBackStack(null);
        transaccionFragment.commit();
    }

    public void toBases(Boolean train) {
        if(train){
            bottom.setVisibility(View.GONE);
            extFAB.setVisibility(View.GONE);
        } else {
            bottom.setVisibility(View.VISIBLE);
            extFAB.setVisibility(View.VISIBLE);
            setBasesFAB();
        }

        FragGlobal = new Bases();
        Bundle args = new Bundle();
        args.putBoolean("train", train);
        FragGlobal.setArguments(args);


        transaccionFragment=adminFragment.beginTransaction();
        transaccionFragment.replace(R.id.frameLayout, FragGlobal,null);
        transaccionFragment.addToBackStack(null);
        transaccionFragment.commit();
    }

    public void toModo(boolean desdehome) {
        bottom.setVisibility(View.GONE);
        extFAB.setVisibility(View.GONE);

        //FragGlobal = new Modo();
        Bundle args = new Bundle();
        args.putBoolean("desdehome", desdehome);
        FragGlobal.setArguments(args);

        transaccionFragment=adminFragment.beginTransaction();
        transaccionFragment.replace(R.id.frameLayout, FragGlobal,null);
        transaccionFragment.addToBackStack(null);
        transaccionFragment.commit();
    }

    public void toFrecuencia() {
        //FragGlobal = new Frecuencia();
        transaccionFragment=adminFragment.beginTransaction();
        transaccionFragment.replace(R.id.frameLayout, FragGlobal,null);
        transaccionFragment.addToBackStack(null);
        transaccionFragment.commit();
    }

    public void toDuracion() {
        //FragGlobal = new Duracion();
        transaccionFragment=adminFragment.beginTransaction();
        transaccionFragment.replace(R.id.frameLayout, FragGlobal,null);
        transaccionFragment.addToBackStack(null);
        transaccionFragment.commit();
    }

    public void toCompletado() {
        //FragGlobal = new Completado();
        transaccionFragment=adminFragment.beginTransaction();
        transaccionFragment.replace(R.id.frameLayout, FragGlobal,null);
        transaccionFragment.addToBackStack(null);
        transaccionFragment.commit();
    }




}