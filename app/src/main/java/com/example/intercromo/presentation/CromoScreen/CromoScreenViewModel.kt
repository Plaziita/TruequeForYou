package com.example.intercromo.presentation.CromoScreen

import androidx.lifecycle.ViewModel
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.model.Cromo

class CromoScreenViewModel(cromoRepository: CromoRepository) : ViewModel() {

    val cromoRepository = cromoRepository
     fun getCromo(nombre_: String?): Cromo? {
        return cromoRepository.getCromo(nombre_)
    }
}