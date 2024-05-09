package com.example.intercromo.dao

import com.example.intercromo.model.Conversacion
import com.example.intercromo.model.Mensaje
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date
import java.util.UUID

class ChatRepository {

    private val COLECCION_CONVERSACION = "conversaciones"
    private val COLECCION_MENSAJES = "mensajes"

    private val db = FirebaseFirestore.getInstance()
    private val conversaciones = db.collection(COLECCION_CONVERSACION)

    fun startConversation(user1: String, user2: String): String {
        val conversacionId = UUID.randomUUID().toString()
        val conversacion = Conversacion(conversacionId, user1, user2)
        conversaciones.document(conversacionId).set(conversacion)
        return conversacionId
    }

    fun sendMessage(conversacionId: String, sender: String, message: String) {
        val mensajeId = UUID.randomUUID().toString()
        val mensaje = Mensaje(mensajeId, sender, message, Date())
        db.collection(COLECCION_CONVERSACION)
            .document(conversacionId)
            .collection(COLECCION_MENSAJES)
            .document(mensajeId)
            .set(mensaje)
    }

    fun getConversationMessages(conversacionId: String, callback: (List<Mensaje>) -> Unit) {
        db.collection(COLECCION_CONVERSACION)
            .document(conversacionId)
            .collection(COLECCION_MENSAJES)
            .orderBy("timestamp")
            .get()
            .addOnSuccessListener { result ->
                val messages = mutableListOf<Mensaje>()
                for (document in result) {
                    val mensaje = document.toObject(Mensaje::class.java)
                    messages.add(mensaje)
                }
                callback(messages)
            }
            .addOnFailureListener { exception ->
                // Handle error
                callback(emptyList())
            }
    }

    fun getConversations(userId: String, callback: (List<Conversacion>) -> Unit) {
        conversaciones.whereEqualTo("user1", userId).get()
            .addOnSuccessListener { result1 ->
                val user1Conversations = result1.toObjects(Conversacion::class.java)
                conversaciones.whereEqualTo("user2", userId).get()
                    .addOnSuccessListener { result2 ->
                        val user2Conversations = result2.toObjects(Conversacion::class.java)
                        val conversations = mutableListOf<Conversacion>()
                        conversations.addAll(user1Conversations)
                        conversations.addAll(user2Conversations)
                        callback(conversations)
                    }
                    .addOnFailureListener { exception ->
                        // Handle error
                        callback(emptyList())
                    }
            }
            .addOnFailureListener { exception ->
                // Handle error
                callback(emptyList())
            }
    }


}
