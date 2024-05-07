package com.example.intercromo.presentation.inicio

import android.util.Log
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
    var query = mutableStateOf("")

    init {
        viewModelScope.launch {
            listaCromos.value = cromoRepository.getCromos()
            Log.e("query",query.value)
        }
    }

}