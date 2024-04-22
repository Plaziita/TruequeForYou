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

    init {
        viewModelScope.launch {
            listaCromos.value = cromoRepository.getCromos()
        }
    }
}