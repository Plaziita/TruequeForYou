package com.example.intercromo.presentation.intercambios

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.dao.IntercambiosRepository
import com.example.intercromo.model.Cromo
import com.example.intercromo.model.Intercambios
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class IntercambiosViewModel( val repository: IntercambiosRepository, cromoRepository: CromoRepository) : ViewModel() {

    val listaCromos: MutableState<List<Cromo>> = mutableStateOf(listOf())

    init {
        viewModelScope.launch {
            listaCromos.value = cromoRepository.getCromosAsociados()
        }
    }

    private val userId = Firebase.auth.currentUser?.uid
    private val intercambios = MutableLiveData<List<Intercambios>>()

    fun createIntercambio(idEmisor: String, idRemitente: String, idCromoRemitente: String, idCromoEmisor: String): Boolean {
        return try{
            repository.addIntercambio(idEmisor,idRemitente,idCromoRemitente,idCromoEmisor)
            true
        }catch (e: Exception) {
            false // Hubo un error al agregar el cromo
        }
    }
}

