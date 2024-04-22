package com.example.intercromo.presentation.perfil.adquisiciones

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.dao.UsuarioRepository
import com.example.intercromo.model.Cromo
import kotlinx.coroutines.launch

class AdquisicionesViewModel(cromoRepository: CromoRepository) : ViewModel() {

    val listaCromos: MutableState<List<Cromo>> = mutableStateOf(listOf())


    init {
        viewModelScope.launch {
            listaCromos.value = cromoRepository.getCromos()
        }
    }
}