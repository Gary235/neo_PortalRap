package com.example.neo_portalrap.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.neo_portalrap.Clases.Base;

import java.util.List;

@Entity
public class Entrenamiento {

    @PrimaryKey public int uidEntrenamiento;

    public int modo;
    public int frecuencia;
    public int segundos;
    public int minutos;




}


