package com.example.senda.datos

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "perfil")
data class Perfil(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nombre: String,
    val avatar: Int
)

@Entity(
    tableName = "rutina",
    foreignKeys = [
        ForeignKey(
            entity = Perfil::class,
            parentColumns = ["id"],
            childColumns = ["perfilId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("perfilId")]
)
data class Rutina(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nombre: String,
    val perfilId: Long
)

@Entity(
    tableName = "tarea",
    foreignKeys = [
        ForeignKey(
            entity = Rutina::class,
            parentColumns = ["id"],
            childColumns = ["rutinaId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("rutinaId")]
)
data class Tarea(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nombre: String,
    val pictograma: String,
    val orden: Int,
    val rutinaId: Long
)