package com.example.intercromo.presentation.perfil.misCromos

import androidx.lifecycle.ViewModel
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.model.Cromo

class MisCromosViewModel(cromoRepository: CromoRepository) : ViewModel() {

    val cromoRepository = cromoRepository

     fun getMisCromos():MutableList<Cromo>{
        return cromoRepository.getCromosAsociados();
    }
}