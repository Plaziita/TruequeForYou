package com.example.intercromo.presentation.inicio

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.model.Cromo
import kotlinx.coroutines.launch

class InicioViewModel(cromoRepository: CromoRepository) : ViewModel() {

    val listaCromos: MutableState<List<Cromo>> = mutableStateOf(listOf())
    val cromosFiltrados: MutableState<List<Cromo>> = mutableStateOf(listOf())

    init {
        viewModelScope.launch {
            listaCromos.value = cromoRepository.getCromos()
        }
    }
    fun filtrarCromos(query: String) {
        cromosFiltrados.value = if (query.isEmpty()) {
            listaCromos.value
        } else {
            listaCromos.value.filter { cromo ->
                cromo.nombre.contains(query, ignoreCase = true) || cromo.categoria.contains(query, ignoreCase = true)
            }
        }
    }

}