package com.example.intercromo.presentation.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.intercromo.dao.ChatRepository
import com.example.intercromo.model.Conversacion
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class ChatViewModel(private val chatRepository: ChatRepository) : ViewModel() {

    private val userId = Firebase.auth.currentUser?.uid
    private val _conversations = MutableLiveData<List<Conversacion>>()
    val conversations: LiveData<List<Conversacion>>
        get() = _conversations

    fun loadConversations() {
        userId?.let { userId ->
            chatRepository.getConversations(userId) { conversations ->
                _conversations.postValue(conversations)
            }
        }
    }
}

