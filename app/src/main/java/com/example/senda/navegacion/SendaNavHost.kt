package com.example.senda.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.senda.ui.PantallaCitas
import com.example.senda.ui.PantallaPerfiles
import com.example.senda.ui.PantallaRutinas

object Rutas {
    const val PERFILES = "perfiles"
    const val RUTINAS = "rutinas"
    const val CITAS = "citas"
}

@Composable
fun SendaNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Rutas.PERFILES) {
        composable(Rutas.PERFILES) {
            PantallaPerfiles(
                onIrARutinas = { navController.navigate(Rutas.RUTINAS) }
            )
        }
        composable(Rutas.RUTINAS) {
            PantallaRutinas(
                onIrACitas = { navController.navigate(Rutas.CITAS) },
                onVolver = { navController.popBackStack() }
            )
        }
        composable(Rutas.CITAS) {
            PantallaCitas(
                onVolver = { navController.popBackStack() }
            )
        }
    }
}