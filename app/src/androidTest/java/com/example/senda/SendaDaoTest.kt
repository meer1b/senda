package com.example.senda

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.senda.datos.AppDatabase
import com.example.senda.datos.Perfil
import com.example.senda.datos.Rutina
import com.example.senda.datos.Tarea
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SendaDaoTest {

    private lateinit var context: Context
    private val nombreBd = "senda_test.db"

    @Before
    fun preparar() {
        context = ApplicationProvider.getApplicationContext()
        context.deleteDatabase(nombreBd)
    }

    @After
    fun limpiar() {
        context.deleteDatabase(nombreBd)
    }

    private fun abrirBd(): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, nombreBd).build()

    @Test
    fun rutinaConTresTareasSeGuardaYSeLeeEnOrden() = runBlocking {
        // 1. Guardar
        var bd = abrirBd()
        val perfilId = bd.dao().insertarPerfil(Perfil(nombre = "Leo", avatar = 3))
        val rutinaId = bd.dao().insertarRutina(Rutina(nombre = "Mañana", perfilId = perfilId))
        bd.dao().insertarTareas(
            listOf(
                Tarea(nombre = "Mochila", pictograma = "mochila", orden = 3, rutinaId = rutinaId),
                Tarea(nombre = "Desayunar", pictograma = "desayunar", orden = 1, rutinaId = rutinaId),
                Tarea(nombre = "Vestirse", pictograma = "vestirse", orden = 2, rutinaId = rutinaId)
            )
        )
        bd.close()

        // 2. "Cerrar la app y abrirla": reabrir la base de datos desde el archivo
        bd = abrirBd()
        val tareas = bd.dao().tareasDeRutina(rutinaId)
        bd.close()

        // 3. Comprobar
        assertEquals(3, tareas.size)
        assertEquals(listOf("Desayunar", "Vestirse", "Mochila"), tareas.map { it.nombre })
    }
}