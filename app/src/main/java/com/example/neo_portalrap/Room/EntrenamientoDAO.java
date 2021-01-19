package com.example.neo_portalrap.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface EntrenamientoDAO {


   /* @Transaction
    @Query("SELECT * FROM Entrenamiento")
    List<entrenamientoXbases> obtenerentranmientos();

    @Transaction
    @Query("SELECT max(uidEntrenamiento) FROM Entrenamiento")
    List<entrenamientoXbases> obtenerultimoEntranmientos();

    @Insert
    void insertentrenamiento(Entrenamiento entrenamiento, List<Base_room> conjuntoBaseRooms);
*/

}
