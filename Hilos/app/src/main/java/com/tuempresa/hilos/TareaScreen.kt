package com.tuempresa.hilos

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TareaScreen(viewModel: TareaViewModel = viewModel()) {

    val resultado = viewModel.resultado
    val cargando = viewModel.cargando

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Ejemplo Integral de Hilos") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Estado de la tarea",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(12.dp))

                    if (cargando) {
                        CircularProgressIndicator()
                        Spacer(Modifier.height(8.dp))
                        Text("Procesando...", style = MaterialTheme.typography.bodyMedium)
                    } else {
                        Text(
                            text = resultado,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Spacer(Modifier.height(32.dp))

            Button(
                onClick = { viewModel.ejecutarTareaPesada() },
                enabled = !cargando,
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text("Ejecutar tarea en segundo plano")
            }
        }
    }
}
