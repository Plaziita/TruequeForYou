package com.example.intercromo.presentation.inicio.filtrar

import androidx.lifecycle.ViewModel
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.model.Cromo

class FiltradoViewModel(cromoRepository: CromoRepository) : ViewModel() {


    val cromoRepository = cromoRepository


    fun getCromosFiltrados(query: String): List<Cromo> {
        return cromoRepository.getCromosFiltrados(query)
    }


}