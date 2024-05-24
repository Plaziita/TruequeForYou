package com.example.intercromo.presentation.favoritos

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.model.Cromo
import kotlinx.coroutines.launch

class FavoritosViewModel(cromoRepository: CromoRepository) : ViewModel() {

    val listaCromos: MutableState<List<Cromo>> = mutableStateOf(listOf())


    init {
        viewModelScope.launch {
            listaCromos.value = cromoRepository.getFavoritos()
            Log.d("listafav0s",listaCromos.toString())
        }
    }
}