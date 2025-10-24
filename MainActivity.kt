package com.example.listas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listas.ui.theme.ListasTheme

data class Curso(
    val id: Int,
    var nombre: String,
    val descripcion: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListaCursosApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ListaCursosApp(modifier: Modifier = Modifier) {
    var idInput by remember { mutableStateOf("") }
    var nombreInput by remember { mutableStateOf("") }

    val cursos = remember {
        mutableStateListOf<Curso>().apply {
            for (i in 1..10) {
                add(Curso(i, "Nombre $i", "Descripción del curso $i"))
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Campo ID
        OutlinedTextField(
            value = idInput,
            onValueChange = { idInput = it },
            label = { Text("ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Nombre
        OutlinedTextField(
            value = nombreInput,
            onValueChange = { nombreInput = it },
            label = { Text("Nombre del curso") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón Modificar
        Button(
            onClick = {
                val id = idInput.toIntOrNull()
                if (id != null && id in 1..cursos.size) {
                    val index = id - 1
                    cursos[index] = cursos[index].copy(nombre = nombreInput)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Modificar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botón Ver lista (por ahora sin acción extra)
        Button(
            onClick = { /* futura acción */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver lista")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista scrollable
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(cursos) { curso ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = "ID: ${curso.id}")
                        Text(text = "Nombre: ${curso.nombre}")
                        Text(text = "Descripción: ${curso.descripcion}")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListaCursosPreview() {
    ListasTheme {
        ListaCursosApp()
    }
}
