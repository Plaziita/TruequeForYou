package com.example.intercromo.dao

import android.util.Log
import com.example.intercromo.model.Intercambios
import com.google.firebase.firestore.FirebaseFirestore
import java.sql.Timestamp

class IntercambiosRepository {

    private val COLECCION_INTERCAMBIOS = "intercambios"

    private val db = FirebaseFirestore.getInstance()
    private val intercambios = db.collection(COLECCION_INTERCAMBIOS)

    fun addIntercambio(idEmisor: String, idRemitente: String, idCromoRemitente: String, idCromoEmisor: String) {

        val timestamp = Timestamp(System.currentTimeMillis())
        val idEmisor = idEmisor
        val idRemitente = idRemitente
        val idCromoRemitente = idCromoRemitente
        val idCromoEmisor = idCromoEmisor

        val intercambio = Intercambios(timestamp,idEmisor,idRemitente,idCromoEmisor,idCromoRemitente,"pendiente")

        FirebaseFirestore.getInstance().collection("intercambios").add(intercambio.toMap())
            .addOnSuccessListener {
                it.update("idIntercambio", it.id)
                Log.d("InterCromo", "Creado")
            }.addOnFailureListener {
                Log.d("InterCromo", "Error")
            }

    }
}
