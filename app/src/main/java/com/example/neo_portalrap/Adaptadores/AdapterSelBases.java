package com.example.neo_portalrap.Adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.neo_portalrap.Clases.Base;
import com.example.neo_portalrap.MainActivity;
import com.example.neo_portalrap.R;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class AdapterSelBases extends RecyclerView.Adapter<AdapterSelBases.MyViewHolder> {

    Context micontexto;
    ArrayList<Base> arrayList;

    public AdapterSelBases(Context micontexto, ArrayList<Base> arrayList) {
        this.micontexto = micontexto;
        this.arrayList = arrayList;

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, artista, duracion;
        RoundedImageView imagen;
        ImageView play;
        CheckBox checkBox;

        public MyViewHolder(View v) {
            super(v);
            nombre = v.findViewById(R.id.itemselbase_nombre);
            artista = v.findViewById(R.id.itemselbase_artista);
            duracion = v.findViewById(R.id.itemselbase_duracion);
            play = v.findViewById(R.id.itemselbase_imgplay);
            imagen = v.findViewById(R.id.itemselbase_foto);
            checkBox = v.findViewById(R.id.itemselbase_checkbox);
        }
    }



    @NonNull
    @Override
    public AdapterSelBases.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_selbases, parent, false);

        MyViewHolder vh = new MyViewHolder(v);


        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSelBases.MyViewHolder holder, int position) {

        if (arrayList.get(position).getNombre().length() > 12){ holder.nombre.setTextSize(11); }
        else { holder.nombre.setTextSize(13); }
        holder.nombre.setText(arrayList.get(position).getNombre());

        holder.artista.setText(arrayList.get(position).getArtista());
        holder.duracion.setText(arrayList.get(position).getDuracion());
        holder.play.setImageResource(R.drawable.ic_play);

        StorageReference mStorage = FirebaseStorage.getInstance().getReference();
        mStorage.child("info_bases/imagenes/" + arrayList.get(position).getImagen())
                .getDownloadUrl()
                .addOnSuccessListener(uri ->
                        Glide.with(micontexto)
                                .load(uri)
                                .into(holder.imagen))
                .addOnFailureListener(exception -> {
                    // Handle any errors
                    Log.d("Imagenes" , "error -- " + exception.getMessage());
                    Glide.with(micontexto)
                            .load(android.R.drawable.stat_notify_error)
                            .into(holder.imagen);
                });


        holder.checkBox.setChecked(arrayList.get(position).getSeleccionado());

        holder.checkBox.setOnClickListener(a -> {
            arrayList.get(position).setSeleccionado(!arrayList.get(position).getSeleccionado());
        });

        holder.imagen.setOnClickListener(a -> {

        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
