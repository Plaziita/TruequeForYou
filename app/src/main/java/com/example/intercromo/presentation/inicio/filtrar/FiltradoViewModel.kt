package com.example.intercromo.presentation.inicio.filtrar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.model.Cromo
import kotlinx.coroutines.launch

class FiltradoViewModel (cromoRepository: CromoRepository) : ViewModel() {

    val cromosFiltrados: MutableState<List<Cromo>> = mutableStateOf(listOf())
    val listaCromos: MutableState<List<Cromo>> = mutableStateOf(listOf())
    var query = ""

    init {
        viewModelScope.launch {
            filtrarCromos(query)
        }
    }
    fun filtrarCromos(query: String) {
        cromosFiltrados.value = if (query.isEmpty()) {
            emptyList()
        } else {
            listaCromos.value.filter { cromo ->
                cromo.nombre.contains(query, ignoreCase = true) || cromo.categoria.contains(query, ignoreCase = true)
            }
        }
    }

}