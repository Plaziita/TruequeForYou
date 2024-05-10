package com.example.intercromo.presentation.editarCromo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intercromo.dao.CromoRepository
import kotlinx.coroutines.launch

class EditarCromoViewModel (cromoRepository: CromoRepository): ViewModel(){

    val cromoRepository = cromoRepository

    fun updateCromo(cromoId: String, nombre: String, descripcion: String, imagen: String, categoria: String) {
        viewModelScope.launch {
            cromoRepository.updateCromo(cromoId, nombre, descripcion, imagen, categoria)
        }
    }
}