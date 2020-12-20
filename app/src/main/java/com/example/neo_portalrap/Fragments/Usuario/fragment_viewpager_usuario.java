package com.example.neo_portalrap.Fragments.Usuario;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.neo_portalrap.MainActivity;
import com.example.neo_portalrap.Adaptadores.AdaptadorRecycleView;
import com.example.neo_portalrap.R;

import java.util.ArrayList;


public class fragment_viewpager_usuario extends Fragment {


    public static final String ARG_OBJECT = "object";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mis_bases, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recy_bases_grab);


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        int num_object = args.getInt(ARG_OBJECT);
        Log.d("VER" , "Object: " + num_object);


        recyclerView.setHasFixedSize(true);

        layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


       /* if(num_object == 1){
            //grabaciones usuario / TODAS
            for (int i = 0; i < 120; i++){
                arrayList.add(i);
            }
        }
        else {
            //Bases usuario / TODAS
            for (int i = 120; i > 0; i--){
                arrayList.add(i);
            }
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0){
                    MainActivity.extFAB.hide();
                    //MainActivity.bottom.animate().alpha(0).start();
                    MainActivity.bottom.setVisibility(View.GONE);
                } else{
                    MainActivity.extFAB.show();
                    //MainActivity.bottom.animate().alpha(1).start();
                    MainActivity.bottom.setVisibility(View.VISIBLE);

                }

                super.onScrolled(recyclerView, dx, dy);
            }
        });


        mAdapter = new AdaptadorRecycleView(arrayList);
        recyclerView.setAdapter(mAdapter);
*/
    }
}