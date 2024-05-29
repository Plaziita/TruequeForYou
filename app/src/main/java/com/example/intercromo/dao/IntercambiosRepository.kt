package com.example.intercromo.dao

import android.util.Log
import androidx.navigation.NavController
import com.example.intercromo.model.Intercambios
import com.google.firebase.firestore.FirebaseFirestore
import java.sql.Timestamp

class IntercambiosRepository(controller: NavController) {

    private val COLECCION_INTERCAMBIOS = "intercambios"

    private val db = FirebaseFirestore.getInstance()
    private val intercambios = db.collection(COLECCION_INTERCAMBIOS)
    private val usuarioRepository = UsuarioRepository(controller)

    fun addIntercambio(
        idEmisor: String,
        idRemitente: String,
        idCromoRemitente: String,
        idCromoEmisor: String
    ) {

        val timestamp = Timestamp(System.currentTimeMillis())
        val idEmisor = idEmisor
        val idRemitente = idRemitente
        val idCromoRemitente = idCromoRemitente
        val idCromoEmisor = idCromoEmisor


        val intercambio = Intercambios(
            timestamp,
            idEmisor,
            idRemitente,
            idCromoEmisor,
            idCromoRemitente,
            "pendiente"
        )

        intercambios.add(intercambio.toMap())
            .addOnSuccessListener {
                it.update("idIntercambio", it.id)
                usuarioRepository.anadirIntercambio(it.id, idEmisor)
                usuarioRepository.anadirIntercambio(it.id, idRemitente)
                Log.d("InterCromo", "Creado")
            }.addOnFailureListener {
                Log.d("InterCromo", "Error")
            }

    }
}
