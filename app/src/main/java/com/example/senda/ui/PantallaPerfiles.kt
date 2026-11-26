package com.example.senda.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

// Avatar 1 = primer emoji, avatar 2 = segundo... En la base de datos solo se guarda el número.
val AVATARES = listOf("🦊", "🐻", "🐼", "🐯", "🐸", "🦉")

@Composable
fun PantallaPerfiles(
    onIrARutinas: () -> Unit,
    vm: PerfilesViewModel = viewModel()
) {
    var nombre by remember { mutableStateOf("") }
    var avatar by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Perfiles", style = MaterialTheme.typography.headlineMedium)

        // --- Formulario ---
        OutlinedTextField(
            value = nombre,
            onValueChange = {
                nombre = it
                vm.limpiarError()
            },
            label = { Text("Nombre") },
            singleLine = true,
            isError = vm.errorNombre != null,
            supportingText = { vm.errorNombre?.let { Text(it) } },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Elige un avatar")
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            AVATARES.forEachIndexed { indice, emoji ->
                val numero = indice + 1
                val seleccionado = avatar == numero
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(
                            if (seleccionado) MaterialTheme.colorScheme.primaryContainer
                            else MaterialTheme.colorScheme.surfaceVariant
                        )
                        .then(
                            if (seleccionado)
                                Modifier.border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                            else Modifier
                        )
                        .clickable { avatar = numero },
                    contentAlignment = Alignment.Center
                ) {
                    Text(emoji, fontSize = 24.sp)
                }
            }
        }

        Button(
            onClick = {
                if (vm.crearPerfil(nombre, avatar)) {
                    nombre = ""
                    avatar = 1
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear perfil")
        }

        // --- Lista de perfiles guardados ---
        Text("Perfiles guardados", style = MaterialTheme.typography.titleMedium)

        if (vm.perfiles.isEmpty()) {
            Text("Todavía no hay perfiles")
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(vm.perfiles, key = { it.id }) { perfil ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(AVATARES.getOrElse(perfil.avatar - 1) { "🙂" }, fontSize = 32.sp)
                    Text(perfil.nombre, style = MaterialTheme.typography.titleMedium)
                }
            }
        }

        OutlinedButton(onClick = onIrARutinas, modifier = Modifier.fillMaxWidth()) {
            Text("Ir a Rutinas")
        }
    }
}