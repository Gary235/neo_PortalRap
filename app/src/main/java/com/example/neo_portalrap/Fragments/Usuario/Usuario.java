package com.example.neo_portalrap.Fragments.Usuario;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.neo_portalrap.Adaptadores.PagerAdapterUsuario;
import com.example.neo_portalrap.MainActivity;
import com.example.neo_portalrap.R;
import com.google.android.material.tabs.TabLayout;


public class Usuario extends Fragment  {


    androidx.appcompat.widget.Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    PagerAdapterUsuario pagerAdapterUsuario;

    ImageButton btnEditar;
    Button btnFav;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.exit_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button

            case R.id.logout:
                AlertDialog.Builder mensaje;
                mensaje = new AlertDialog.Builder(getActivity());
                mensaje.setTitle("Usuario");
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
        View v = inflater.inflate(R.layout.fragment_usuario, container, false);

        if(MainActivity.recien_entro_usuario){
            final Handler handler = new Handler();
            handler.postDelayed(() -> {

                MainActivity.extFAB.shrink();
            }, 4000);
            MainActivity.recien_entro_usuario = false;
        }

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

        toolbar = v.findViewById(R.id.toolbar_usuario);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);



        return v;
    }


}