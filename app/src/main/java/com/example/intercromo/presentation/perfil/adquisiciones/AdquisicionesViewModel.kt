package com.example.intercromo.presentation.perfil.adquisiciones

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.dao.UsuarioRepository
import com.example.intercromo.model.Cromo

class AdquisicionesViewModel(private val cromoRepository: CromoRepository) : ViewModel() {

    private val _listaCromos = MutableLiveData<List<Cromo>>()
    val listaCromos: LiveData<List<Cromo>> = _listaCromos

    fun getList(listaCromos : List<Cromo>){
        _listaCromos.value = listaCromos


    }
}