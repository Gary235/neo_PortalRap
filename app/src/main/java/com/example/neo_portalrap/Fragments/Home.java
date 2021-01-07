package com.example.neo_portalrap.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.example.neo_portalrap.MainActivity;
import com.example.neo_portalrap.R;


public class Home extends Fragment {

    androidx.appcompat.widget.Toolbar toolbar;
    Button btnPrueba;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.configuracion_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.configuracion:
                AlertDialog.Builder mensaje;
                mensaje = new AlertDialog.Builder(getActivity());
                mensaje.setTitle("Home");
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
        boolean desdeentrenamiento = args.getBoolean("desdeentrenamiento");


        View v = inflater.inflate(R.layout.fragment_home, container, false);

        v.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);

                if (desdeentrenamiento){

                    int cx = (int)MainActivity.extFAB.getX();
                    int cy = (int)MainActivity.extFAB.getY();
                    int width = v.getWidth();
                    int height = v.getHeight();


                    float finalRadius = Math.max(width, height) / 2 + Math.max(width - cx, height - cy);
                    Animator anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, finalRadius, 0);
                    anim.setDuration(300);
                    anim.start();
                }


            }
        });

        if(MainActivity.recien_entro_home){
            final Handler handler = new Handler();
            handler.postDelayed(() -> {

                MainActivity.extFAB.shrink();
            }, 4000);
            MainActivity.recien_entro_home = false;
        }


        toolbar = v.findViewById(R.id.toolbar_home);
        setHasOptionsMenu(true);

        toolbar.setTitle("Hola, Gary");

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        btnPrueba = v.findViewById(R.id.buttonprueba);

        btnPrueba.setOnClickListener(a -> {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.toPrueba();
        });

        return v;
    }

    interface Dismissible {
        interface OnDismissedListener {
            void onDismissed();
        }

        void dismiss(OnDismissedListener listener);
    }



}