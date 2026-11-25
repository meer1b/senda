package com.example.senda.datos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SendaDao {

    @Insert
    suspend fun insertarPerfil(perfil: Perfil): Long

    @Insert
    suspend fun insertarRutina(rutina: Rutina): Long

    @Insert
    suspend fun insertarTareas(tareas: List<Tarea>)

    @Query("SELECT * FROM perfil ORDER BY nombre")
    suspend fun listarPerfiles(): List<Perfil>

    @Query("SELECT * FROM rutina WHERE perfilId = :perfilId ORDER BY nombre")
    suspend fun rutinasDePerfil(perfilId: Long): List<Rutina>

    @Query("SELECT * FROM tarea WHERE rutinaId = :rutinaId ORDER BY orden")
    suspend fun tareasDeRutina(rutinaId: Long): List<Tarea>
}