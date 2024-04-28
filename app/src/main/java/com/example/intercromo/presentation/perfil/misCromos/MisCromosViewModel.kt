package com.example.intercromo.presentation.perfil.misCromos

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.model.Cromo
import kotlinx.coroutines.launch

class MisCromosViewModel(cromoRepository: CromoRepository) : ViewModel() {

    val listaCromos: MutableState<List<Cromo>> = mutableStateOf(listOf())

    init {
        viewModelScope.launch {
            listaCromos.value = cromoRepository.getCromosAsociados()
        }
    }
}