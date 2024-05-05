package com.example.intercromo.presentation.CromoScreen

import androidx.lifecycle.ViewModel
import com.example.intercromo.dao.ChatRepository
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.model.Cromo
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class CromoScreenViewModel(cromoRepository: CromoRepository, repository: ChatRepository) : ViewModel() {

    val cromoRepository = cromoRepository
    val repository = repository
    val userId = Firebase.auth.currentUser?.uid

     fun getCromo(nombre_: String?): Cromo? {
        return cromoRepository.getCromo(nombre_)
    }

    fun startConversation(user1: String, user2: String) {
        repository.startConversation(user1, user2)
    }

}