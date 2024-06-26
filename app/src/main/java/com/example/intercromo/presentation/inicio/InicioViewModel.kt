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
    val cromosRecientes : MutableState<List<Cromo>> = mutableStateOf(listOf())
    val query:MutableState<String> = mutableStateOf("")

    init {
        viewModelScope.launch {
            listaCromos.value = cromoRepository.getCromos()
            cromosRecientes.value = cromoRepository.getCromosRecientes()
            Log.e("query",query.value)
        }
    }

    fun queryChanged(queryp: String){
        query.value = queryp
    }




}