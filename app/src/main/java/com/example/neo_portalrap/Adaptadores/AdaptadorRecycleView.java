package com.example.neo_portalrap.Adaptadores;

import android.app.AlertDialog;
import android.content.Context;

import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.neo_portalrap.Clases.Base;
import com.example.neo_portalrap.Fragments.Bases;
import com.example.neo_portalrap.R;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AdaptadorRecycleView extends RecyclerView.Adapter<AdaptadorRecycleView.MyViewHolder> {
    ArrayList<Base> mDataset;
    final ArrayList<Base> mDatasetcopy = new ArrayList<>();
    Context miContexto;
    public static boolean isNetworkConnected = false;
    String tag;
    String path_file, carpeta = "/neo portal rap/bases/";
    File localFile;
    File file;

    public static MediaPlayer basesPlayer = new MediaPlayer();

    public AdaptadorRecycleView(ArrayList<Base> myDataset, Context context, String tag) {
        this.mDataset = myDataset;
        this.miContexto = context;
        this.tag = tag;

        mDatasetcopy.addAll(mDataset);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) miContexto.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkRequest.Builder builder = new NetworkRequest.Builder();

            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback(){
                @Override
                public void onAvailable(Network network) {
                    isNetworkConnected = true; // Global Static Variable
                }
                @Override
                public void onLost(Network network) {
                    isNetworkConnected = false; // Global Static Variable
                }
            }

            );
            isNetworkConnected = false;
        }
        catch (Exception e){
            isNetworkConnected = false;
        }

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

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference mStorage = storage.getReference();
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


        if (encontrarBeat(mDataset.get(position).getUrl())){
            holder.descargar.setImageResource(R.drawable.ic_descargado);
        }   else {
            holder.descargar.setImageResource(R.drawable.ic_descargar);
        }

        holder.descargar.setOnClickListener(a -> {

            if (!encontrarBeat(mDataset.get(position).getUrl())){
                holder.progress_descarga.setVisibility(View.VISIBLE);
                if(isNetworkConnected){
                StorageReference beatRef = mStorage.child("info_bases/bases/" + mDataset.get(position).getUrl());

                path_file = Environment.getExternalStorageDirectory() + carpeta;
                localFile = new File(path_file);

                if (!localFile.exists()) {
                    localFile.mkdirs();
                }
                file = new File(localFile,  mDataset.get(position).getUrl());

                beatRef.getFile(file).addOnSuccessListener(taskSnapshot -> {
                    // Local temp file has been created

                    holder.descargar.setImageResource(R.drawable.ic_descargado);
                    holder.progress_descarga.setVisibility(View.GONE);
                    Toast.makeText(miContexto, "Beat Descargado con exito", Toast.LENGTH_LONG).show();
                })
                        .addOnFailureListener(exception -> {
                    // Handle any errors
                    Toast.makeText(miContexto, "Error en la descarga", Toast.LENGTH_LONG).show();

                });
                }
                else {
                    Toast.makeText(miContexto, "Error de red", Toast.LENGTH_LONG).show();
                }
            }
            else {
                    // borrar beat
                holder.progress_descarga.setVisibility(View.VISIBLE);
                AlertDialog.Builder mensaje;
                mensaje = new AlertDialog.Builder(miContexto);
                mensaje.setTitle("Eliminar Base");
                mensaje.setMessage(" \n Se borrara la base de tu almacenamiento, pero seguiras pudiendo escucharlo a traves de internet\n \n");
                mensaje.setPositiveButton("Eliminar", (dialog, which) -> {
                    String path = Environment.getExternalStorageDirectory().toString() +
                            "/neo portal rap/bases/" +
                            mDataset.get(position).getUrl();

                    File fileaeliminar = new File(path);
                    boolean deleted = fileaeliminar.delete();
                    if(deleted){
                        holder.descargar.setImageResource(R.drawable.ic_descargar);
                        Toast.makeText(miContexto, "Base Eliminada", Toast.LENGTH_SHORT).show();

                    }
                    else
                        Toast.makeText(miContexto, "Error en la eliminación", Toast.LENGTH_SHORT).show();

                    holder.progress_descarga.setVisibility(View.GONE);

                });
                mensaje.setNegativeButton("Cancelar", ((dialog, which) -> {
                    dialog.cancel();
                }));
                mensaje.create();
                mensaje.show();
            }



        });


        holder.imagen.setOnClickListener(a -> {

            Log.d("miMedia", "pos -- " + position);
            Log.d("miMedia", "base -- " + mDataset.get(position).getNombre());
            Log.d("miMedia", "mediapos -- " + Bases.mediaPos);

            if (encontrarBeat(mDataset.get(position).getUrl())){
                //esta descargada
                Log.d("miMedia", "Esta descargada -");
                if (Bases.mediaPos != position){
                    Log.d("miMedia", "otra base clickeada");

                    if (Bases.mediacounter > 0){
                        Log.d("miMedia", "primera base clickeada");

                        Bases.mediaPosAnt = Bases.mediaPos;
                        notifyItemChanged(Bases.mediaPosAnt);
                        basesPlayer.stop();
                        basesPlayer.reset();
                        Log.d("miMedia", "mediacounter -- " + Bases.mediacounter);

                    }

                    String path = Environment.getExternalStorageDirectory().toString() +
                            "/neo portal rap/bases/" +
                            mDataset.get(position).getUrl();

                    try {
                        basesPlayer.setDataSource(path);
                        basesPlayer.prepare();
                    } catch (IOException exception) { exception.printStackTrace(); }

                    basesPlayer.start();
                    holder.play.setVisibility(View.GONE);
                    holder.lottie_audio.setVisibility(View.VISIBLE);
                    holder.lottie_audio.playAnimation();
                    holder.lottie_audio.loop(true);

                    Bases.mediaPos = position;
                    Bases.mediacounter ++;

                    Log.d("miMedia", "base starteada");
                    Log.d("miMedia", "mediapos cambiada -- " + Bases.mediaPos);

                }
                else if (basesPlayer.isPlaying() && Bases.mediaPos == position){
                    Log.d("miMedia", "base pausada -- " );

                    basesPlayer.pause();
                    holder.play.setVisibility(View.VISIBLE);
                    holder.lottie_audio.setVisibility(View.GONE);
                    holder.lottie_audio.cancelAnimation();


                }
                else if (!basesPlayer.isPlaying() && Bases.mediaPos == position){
                    Log.d("miMedia", "base starteada -- " );

                    basesPlayer.start();
                    holder.play.setVisibility(View.GONE);
                    holder.lottie_audio.setVisibility(View.VISIBLE);
                    holder.lottie_audio.playAnimation();
                    holder.lottie_audio.loop(true);
                }


            }
            else {
                //no esta descargada
                if (isNetworkConnected){
                    Log.d("miMedia", "base stremeada -- " );
                    if (Bases.mediaPos != position){
                        Log.d("miMedia", "otra base clickeada");

                        holder.progress_stream.setVisibility(View.VISIBLE);
                        if (Bases.mediacounter > 0){
                            Log.d("miMedia", "primera base clickeada");

                            Bases.mediaPosAnt = Bases.mediaPos;
                            notifyItemChanged(Bases.mediaPosAnt);
                            basesPlayer.stop();
                            basesPlayer.reset();
                            Log.d("miMedia", "mediacounter -- " + Bases.mediacounter);

                        }
                        StorageReference beatref = mStorage.child("info_bases/bases/" + mDataset.get(position).getUrl());
                        beatref.getDownloadUrl()
                                .addOnSuccessListener(uri -> {

                                    try {
                                        // Download url of file
                                        final String url = uri.toString();
                                        basesPlayer.setDataSource(url);
                                        // wait for media player to get prepare
                                        basesPlayer.setOnPreparedListener(mp ->{
                                            mp.start();
                                            holder.progress_stream.setVisibility(View.GONE);

                                        });
                                        basesPlayer.prepareAsync();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                })
                                .addOnFailureListener(e -> { Log.i("TAG", e.getMessage()); });


                        Bases.mediaPos = position;
                        Bases.mediacounter ++;

                        Log.d("miMedia", "base starteada");
                        Log.d("miMedia", "mediapos cambiada -- " + Bases.mediaPos);

                    }

                    else if (basesPlayer.isPlaying() && Bases.mediaPos == position){
                        Log.d("miMedia", "base pausada -- " );


                        basesPlayer.pause();
                        holder.play.setVisibility(View.VISIBLE);
                        holder.lottie_audio.setVisibility(View.GONE);
                        holder.lottie_audio.cancelAnimation();



                    }
                    else if (!basesPlayer.isPlaying() && Bases.mediaPos == position){
                        Log.d("miMedia", "base starteada -- " );

                        basesPlayer.start();
                        holder.play.setVisibility(View.GONE);
                        holder.lottie_audio.setVisibility(View.VISIBLE);
                        holder.lottie_audio.playAnimation();
                        holder.lottie_audio.loop(true);
                    }


                }
                else {
                    Toast.makeText(miContexto, "Error de red", Toast.LENGTH_LONG).show();

                }


            }



        });
        holder.play.setOnClickListener(a -> {

            Log.d("miMedia", "pos -- " + position);
            Log.d("miMedia", "base -- " + mDataset.get(position).getNombre());
            Log.d("miMedia", "mediapos -- " + Bases.mediaPos);

            if (encontrarBeat(mDataset.get(position).getUrl())){
                //esta descargada
                Log.d("miMedia", "Esta descargada -");
                if (Bases.mediaPos != position){
                    Log.d("miMedia", "otra base clickeada");

                    if (Bases.mediacounter > 0){
                        Log.d("miMedia", "primera base clickeada");

                        Bases.mediaPosAnt = Bases.mediaPos;
                        notifyItemChanged(Bases.mediaPosAnt);
                        basesPlayer.stop();
                        basesPlayer.reset();
                        Log.d("miMedia", "mediacounter -- " + Bases.mediacounter);

                    }

                    String path = Environment.getExternalStorageDirectory().toString() +
                            "/neo portal rap/bases/" +
                            mDataset.get(position).getUrl();

                    try {
                        basesPlayer.setDataSource(path);
                        basesPlayer.prepare();
                    } catch (IOException exception) { exception.printStackTrace(); }

                    basesPlayer.start();
                    holder.play.setVisibility(View.GONE);
                    holder.lottie_audio.setVisibility(View.VISIBLE);
                    holder.lottie_audio.playAnimation();
                    holder.lottie_audio.loop(true);

                    Bases.mediaPos = position;
                    Bases.mediacounter ++;

                    Log.d("miMedia", "base starteada");
                    Log.d("miMedia", "mediapos cambiada -- " + Bases.mediaPos);

                }
                else if (basesPlayer.isPlaying() && Bases.mediaPos == position){
                    Log.d("miMedia", "base pausada -- " );

                    basesPlayer.pause();
                    holder.play.setVisibility(View.VISIBLE);
                    holder.lottie_audio.setVisibility(View.GONE);
                    holder.lottie_audio.cancelAnimation();


                }
                else if (!basesPlayer.isPlaying() && Bases.mediaPos == position){
                    Log.d("miMedia", "base starteada -- " );

                    basesPlayer.start();
                    holder.play.setVisibility(View.GONE);
                    holder.lottie_audio.setVisibility(View.VISIBLE);
                    holder.lottie_audio.playAnimation();
                    holder.lottie_audio.loop(true);
                }


            }
            else {
                //no esta descargada
                if (isNetworkConnected){
                    Log.d("miMedia", "base stremeada -- " );
                    if (Bases.mediaPos != position){
                        Log.d("miMedia", "otra base clickeada");

                        holder.progress_stream.setVisibility(View.VISIBLE);
                        if (Bases.mediacounter > 0){
                            Log.d("miMedia", "primera base clickeada");

                            Bases.mediaPosAnt = Bases.mediaPos;
                            notifyItemChanged(Bases.mediaPosAnt);
                            basesPlayer.stop();
                            basesPlayer.reset();
                            Log.d("miMedia", "mediacounter -- " + Bases.mediacounter);

                        }
                        StorageReference beatref = mStorage.child("info_bases/bases/" + mDataset.get(position).getUrl());
                        beatref.getDownloadUrl()
                                .addOnSuccessListener(uri -> {

                                    try {
                                        // Download url of file
                                        final String url = uri.toString();
                                        basesPlayer.setDataSource(url);
                                        // wait for media player to get prepare
                                        basesPlayer.setOnPreparedListener(mp ->{
                                            mp.start();
                                            holder.progress_stream.setVisibility(View.GONE);

                                        });
                                        basesPlayer.prepareAsync();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                })
                                .addOnFailureListener(e -> { Log.i("TAG", e.getMessage()); });


                        Bases.mediaPos = position;
                        Bases.mediacounter ++;

                        Log.d("miMedia", "base starteada");
                        Log.d("miMedia", "mediapos cambiada -- " + Bases.mediaPos);

                    }

                    else if (basesPlayer.isPlaying() && Bases.mediaPos == position){
                        Log.d("miMedia", "base pausada -- " );


                        basesPlayer.pause();
                        holder.play.setVisibility(View.VISIBLE);
                        holder.lottie_audio.setVisibility(View.GONE);
                        holder.lottie_audio.cancelAnimation();



                    }
                    else if (!basesPlayer.isPlaying() && Bases.mediaPos == position){
                        Log.d("miMedia", "base starteada -- " );

                        basesPlayer.start();
                        holder.play.setVisibility(View.GONE);
                        holder.lottie_audio.setVisibility(View.VISIBLE);
                        holder.lottie_audio.playAnimation();
                        holder.lottie_audio.loop(true);
                    }


                }
                else {
                    Toast.makeText(miContexto, "Error de red", Toast.LENGTH_LONG).show();

                }


            }



        });

    }

    @Override
    public AdaptadorRecycleView.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler, parent, false);

        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    public Boolean encontrarBeat(String nombre){
        String path = Environment.getExternalStorageDirectory().toString() +
                "/neo portal rap/bases/" +
                nombre;
        File f = new File(path);


        return f.exists();
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

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, artista, duracion;
        RoundedImageView imagen;
        ImageButton play, fav;
        ImageView descargar;
        CircularProgressIndicator progress_descarga, progress_stream;
        LottieAnimationView lottie_audio;

        public MyViewHolder(View v) {
            super(v);
            nombre = v.findViewById(R.id.txt_nombre_base);
            artista = v.findViewById(R.id.txt_artista_base);
            duracion = v.findViewById(R.id.txt_duracion_base);
            play = v.findViewById(R.id.btn_play_base);
            fav = v.findViewById(R.id.btn_fav_base);
            imagen = v.findViewById(R.id.img_base);
            descargar = v.findViewById(R.id.descargar);
            progress_descarga = v.findViewById(R.id.carga_descarga);
            progress_stream = v.findViewById(R.id.carga_stream);
            lottie_audio = v.findViewById(R.id.lottie_audio);


        }
    }


}