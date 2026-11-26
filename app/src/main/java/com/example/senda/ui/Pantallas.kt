package com.example.senda.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaRutinas(onIrACitas: () -> Unit, onVolver: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().safeDrawingPadding().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Rutinas", style = MaterialTheme.typography.headlineMedium)
        Text("Pantalla vacía (la rellena T05)")
        Button(onClick = onIrACitas) { Text("Ir a Citas") }
        Button(onClick = onVolver) { Text("Volver") }
    }
}

@Composable
fun PantallaCitas(onVolver: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().safeDrawingPadding().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Citas", style = MaterialTheme.typography.headlineMedium)
        Text("Pantalla vacía (la rellena T08)")
        Button(onClick = onVolver) { Text("Volver") }
    }
}