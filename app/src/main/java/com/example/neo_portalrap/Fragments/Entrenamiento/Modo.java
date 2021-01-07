package com.example.neo_portalrap.Fragments.Entrenamiento;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.neo_portalrap.Fragments.Home;
import com.example.neo_portalrap.MainActivity;
import com.example.neo_portalrap.R;

import java.util.List;


public class Modo extends Fragment {

    androidx.appcompat.widget.Toolbar toolbar;
    ImageButton btnSiguiente;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.ayuda_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.toHome(true);
                break;
            case R.id.home_ayuda:
                AlertDialog.Builder mensaje;
                mensaje = new AlertDialog.Builder(getActivity());
                mensaje.setTitle("Modo");
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
        boolean desdehome = false;
        if(args != null){
            desdehome = args.getBoolean("desdehome");
        }
        MainActivity.bottom.setVisibility(View.GONE);
        MainActivity.extFAB.setVisibility(View.GONE);

        View v = inflater.inflate(R.layout.fragment_modo, container, false);



        boolean finalDesdehome = desdehome;
        v.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (finalDesdehome){
                    v.removeOnLayoutChangeListener(this);
                    int cx = (int)MainActivity.extFAB.getX();
                    int cy = (int)MainActivity.extFAB.getY();
                    int width = v.getWidth();
                    int height = v.getHeight();


                    float finalRadius = Math.max(width, height) / 2 + Math.max(width - cx, height - cy);
                    Animator anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, finalRadius);
                    //anim.setDuration(300);
                    anim.start();
                }


            }
        });


        toolbar = v.findViewById(R.id.toolbar_modo);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);


        btnSiguiente = v.findViewById(R.id.btn_siguiente_modo);

        btnSiguiente.setOnClickListener(s ->  {

            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.toFrecuencia();

        });


        return v;
    }
}