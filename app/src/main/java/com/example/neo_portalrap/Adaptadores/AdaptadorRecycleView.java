package com.example.neo_portalrap.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.neo_portalrap.Clases.Base;
import com.example.neo_portalrap.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class AdaptadorRecycleView extends RecyclerView.Adapter<AdaptadorRecycleView.MyViewHolder> {
    ArrayList<Base> mDataset;
    Context miContexto;

    public AdaptadorRecycleView(ArrayList<Base> myDataset, Context context) {
        this.mDataset = myDataset;
        this.miContexto = context;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, artista, duracion;
        RoundedImageView imagen;
        ImageButton play, fav;

        public MyViewHolder(View v) {
            super(v);
            nombre = v.findViewById(R.id.txt_nombre_base);
            artista = v.findViewById(R.id.txt_artista_base);
            duracion = v.findViewById(R.id.txt_duracion_base);
            play = v.findViewById(R.id.btn_play_base);
            fav = v.findViewById(R.id.btn_fav_base);
            imagen = v.findViewById(R.id.img_base);

        }
    }

    @Override
    public AdaptadorRecycleView.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {

        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler, parent, false);

        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.artista.setText(mDataset.get(position).getArtista());
        holder.duracion.setText(mDataset.get(position).getDuracion());
        holder.nombre.setText(mDataset.get(position).getNombre());
        holder.play.setImageResource(R.drawable.ic_play);
        holder.fav.setImageResource(R.drawable.ic_corazon_vacio);

        holder.imagen.setImageResource(R.drawable.foto);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}