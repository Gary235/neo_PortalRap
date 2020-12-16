package com.example.neo_portalrap.Pruebas;

import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.neo_portalrap.Adaptadores.AdaptadorRecycleView;
import com.example.neo_portalrap.MainActivity;
import com.example.neo_portalrap.R;

import java.util.ArrayList;


public class prueba1 extends android.app.Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<Integer> arrayList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_prueba1, container, false);



        //recyclerView = (RecyclerView) v.findViewById(R.id.lst_prueba);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        for (int i = 0; i < 120; i++){
            arrayList.add(i);

        }
        // specify an adapter (see also next example)
        mAdapter = new AdaptadorRecycleView(arrayList);
        recyclerView.setAdapter(mAdapter);

        int width, height;
        Display display = ((AppCompatActivity)getActivity()).getWindowManager().getDefaultDisplay();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB_MR1) {
            Point size = new Point();
            display.getSize(size);
            width = size.x;
            height = size.y;
        } else {
            width   = display.getWidth();
            height  = display.getHeight();
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0){
                    MainActivity.FAB.hide();
                    //MainActivity.bottom.animate().alpha(0).start();
                    MainActivity.bottom.setVisibility(View.GONE);
                } else{
                    MainActivity.FAB.show();
                    //MainActivity.bottom.animate().alpha(1).start();
                    MainActivity.bottom.setVisibility(View.VISIBLE);

                }

                super.onScrolled(recyclerView, dx, dy);
            }
        });


        return v;
    }
}