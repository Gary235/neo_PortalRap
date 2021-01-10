package com.example.neo_portalrap.Adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.neo_portalrap.Clases.Base;
import com.example.neo_portalrap.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class AdaptadorRecycleView extends RecyclerView.Adapter<AdaptadorRecycleView.MyViewHolder> {
    ArrayList<Base> mDataset;
    final ArrayList<Base> mDatasetcopy = new ArrayList<>();
    Context miContexto;

    public AdaptadorRecycleView(ArrayList<Base> myDataset, Context context) {
        this.mDataset = myDataset;
        this.miContexto = context;

        mDatasetcopy.addAll(mDataset);
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

        if (mDataset.get(position).getNombre().length() > 12){
            holder.nombre.setTextSize(11);
        }
        else {
            holder.nombre.setTextSize(13);
        }
        holder.nombre.setText(mDataset.get(position).getNombre());
        holder.play.setImageResource(R.drawable.ic_play);
        holder.fav.setImageResource(R.drawable.ic_corazon_vacio);

        StorageReference mStorage = FirebaseStorage.getInstance().getReference();
        mStorage.child("info_bases/imagenes/" + mDataset.get(position).getImagen())
                .getDownloadUrl()
                .addOnSuccessListener(uri ->
                        Glide.with(miContexto)
                        .load(uri)
                        .into(holder.imagen))
                .addOnFailureListener(exception -> {
                            // Handle any errors
                    Log.d("Imagenes" , "error -- " + exception.getMessage());
                            Glide.with(miContexto)
                                    .load(android.R.drawable.stat_notify_error)
                                    .into(holder.imagen);
                        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void filter(String text) {

        mDataset.clear();
        if(text.isEmpty()){
            mDataset.addAll(mDatasetcopy);
        } else{
            text = text.toLowerCase();
            for(int i = 0 ; i < mDatasetcopy.size(); i++){
                if(mDatasetcopy.get(i).getNombre().toLowerCase().contains(text) || mDatasetcopy.get(i).getArtista().toLowerCase().contains(text)){
                    mDataset.add(mDatasetcopy.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }


}