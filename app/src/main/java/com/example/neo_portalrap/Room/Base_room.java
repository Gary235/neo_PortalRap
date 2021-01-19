package com.example.neo_portalrap.Room;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Base_room {


    @PrimaryKey public int uid;

    public int idEntrenamiento;

    public String artista;
    public String duracion;
    public String imagen;
    public String nombre;
    public String url;
    public Boolean seleccionado;
}
