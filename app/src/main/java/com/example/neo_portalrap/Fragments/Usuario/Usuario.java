package com.example.neo_portalrap.Fragments.Usuario;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.neo_portalrap.Adaptadores.PagerAdapterUsuario;
import com.example.neo_portalrap.MainActivity;
import com.example.neo_portalrap.R;
import com.google.android.material.tabs.TabLayout;


public class Usuario extends Fragment  {

    ViewPager viewPager;
    TabLayout tabLayout;
    PagerAdapterUsuario pagerAdapterUsuario;

    ImageButton btnFav,btnEditar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_usuario, container, false);

        btnEditar = v.findViewById(R.id.btn_editar_perfil);
        btnFav = v.findViewById(R.id.btn_Favoritos);
        viewPager = v.findViewById(R.id.viewpager_usuario);
        tabLayout= v.findViewById(R.id.tab_layout_usuario);
        pagerAdapterUsuario = new PagerAdapterUsuario( ((AppCompatActivity)getActivity()).getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapterUsuario);
        tabLayout.setupWithViewPager(viewPager);

        int[] tabIcons = {
                R.drawable.ic_grabaciones,
                R.drawable.ic_mis_bases
        };
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);


        btnFav.setOnClickListener(a -> {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.toFavoritos();

        });

        btnEditar.setOnClickListener(a -> {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.toEditarPerfil();

        });

        return v;
    }


}