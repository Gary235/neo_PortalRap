package com.example.neo_portalrap.Room;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Entrenamiento.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract EntrenamientoDAO Dao();





}