package com.example.neo_portalrap.Pruebas;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.neo_portalrap.Adaptadores.AdaptadorRecycleView;
import com.example.neo_portalrap.MainActivity;
import com.example.neo_portalrap.R;

import java.util.ArrayList;


public class prueba1 extends android.app.Fragment {

    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_prueba1, container, false);

        button = v.findViewById(R.id.btnprueba);
        v.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {




            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                int cx = (int)MainActivity.FAB.getX();
                int cy = (int)MainActivity.FAB.getY();
                int width = v.getWidth();
                int height = v.getHeight();


                float finalRadius = Math.max(width, height) / 2 + Math.max(width - cx, height - cy);
                Animator anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, finalRadius);
                anim.setDuration(300);
                anim.start();
            }
        });




        return v;
    }
}