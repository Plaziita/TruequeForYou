package com.example.intercromo.presentation.perfil.historial

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.dao.IntercambiosRepository
import com.example.intercromo.dao.UsuarioRepository
import com.example.intercromo.model.Cromo
import com.example.intercromo.model.Intercambios
import kotlinx.coroutines.launch

class HistorialViewModel (val repository: IntercambiosRepository, val cromoRepository: CromoRepository,val userRepository: UsuarioRepository) : ViewModel() {

    val intercambios: MutableState<List<Intercambios>> = mutableStateOf(listOf())
    val cromos: MutableState<List<Cromo>> = mutableStateOf(listOf())

    init {
        cargarHistorial()
    }

    private fun cargarHistorial() {
        viewModelScope.launch {
            val (intercambiosList, cromoIdsList) = repository.getHistorialIntercambios()
            intercambios.value = intercambiosList
            val cromosList = cromoRepository.getCromosById(cromoIdsList)
            cromos.value = cromosList
        }
    }

    fun cargarNombre(userId: String, onNombreObtenido: (String?) -> Unit) {
        viewModelScope.launch {
            val nombre = userRepository.getNombre(userId)
            onNombreObtenido(nombre)
        }
    }

    fun nombreLocal(): String? {
        return userRepository.getNombreUsuario()
    }


}