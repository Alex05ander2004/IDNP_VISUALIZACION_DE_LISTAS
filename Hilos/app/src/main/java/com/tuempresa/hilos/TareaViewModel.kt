package com.tuempresa.hilos

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TareaViewModel : ViewModel() {

    var resultado by mutableStateOf("Aún no se ha ejecutado la tarea")
        private set

    var cargando by mutableStateOf(false)
        private set

    fun ejecutarTareaPesada() {
        viewModelScope.launch(Dispatchers.IO) {

            cargando = true

            // Simula trabajo pesado en un hilo de fondo
            Thread.sleep(2500)

            val datosProcesados = (1..5000).sum()

            withContext(Dispatchers.Main) {
                resultado = "Resultado del cálculo: $datosProcesados"
                cargando = false
            }
        }
    }
}
