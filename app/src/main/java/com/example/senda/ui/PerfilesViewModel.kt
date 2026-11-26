package com.example.senda.ui

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.senda.datos.AppDatabase
import com.example.senda.datos.Perfil
import kotlinx.coroutines.launch

class PerfilesViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.obtener(application).dao()

    // Lista de perfiles que se ve en pantalla. Cuando cambia, la pantalla se redibuja sola.
    var perfiles by mutableStateOf<List<Perfil>>(emptyList())
        private set

    // Mensaje de error del campo nombre (null = sin error)
    var errorNombre by mutableStateOf<String?>(null)
        private set

    init {
        cargarPerfiles()
    }

    private fun cargarPerfiles() {
        viewModelScope.launch {
            perfiles = dao.listarPerfiles()
        }
    }

    // Devuelve true si se ha creado, false si el nombre no vale
    fun crearPerfil(nombre: String, avatar: Int): Boolean {
        val limpio = nombre.trim()
        if (limpio.isEmpty()) {
            errorNombre = "Escribe un nombre para el perfil"
            return false
        }
        errorNombre = null
        viewModelScope.launch {
            dao.insertarPerfil(Perfil(nombre = limpio, avatar = avatar))
            perfiles = dao.listarPerfiles()
        }
        return true
    }

    fun limpiarError() {
        errorNombre = null
    }
}