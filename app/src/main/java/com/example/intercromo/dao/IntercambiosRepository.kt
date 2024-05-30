package com.example.intercromo.dao

import android.util.Log
import androidx.navigation.NavController
import com.example.intercromo.model.Intercambios
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class IntercambiosRepository(controller: NavController) {

    private val COLECCION_INTERCAMBIOS = "intercambios"

    private val db = FirebaseFirestore.getInstance()
    private val intercambios = db.collection(COLECCION_INTERCAMBIOS)
    private val usuarioRepository = UsuarioRepository(controller)
    private val auth: FirebaseAuth = Firebase.auth

    fun addIntercambio(
        idEmisor: String,
        idRemitente: String,
        idCromoRemitente: String,
        idCromoEmisor: String
    ) {
        val idEmisor = idEmisor
        val idRemitente = idRemitente
        val idCromoRemitente = idCromoRemitente
        val idCromoEmisor = idCromoEmisor


        val intercambio = Intercambios(
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

    suspend fun getIntercambiosByUserId(): Pair<List<Intercambios>, List<String>> {
        val user = auth.currentUser?.uid
        val intercambiosRecibidos = mutableListOf<Intercambios>()
        val cromoIds = mutableListOf<String>()

        if (user != null) {
            try {
                val querySnapshot = intercambios
                    .whereEqualTo("idUserRemitente", user)
                    .get()
                    .await()

                for (document in querySnapshot.documents) {
                    val data = document.data
                    if (data != null) {
                        val idUserEmisor = data["idUserEmisor"] as String
                        val idUserRemitente = data["idUserRemitente"] as String
                        val idCromoEmisor = data["idCromoEmisor"] as String
                        val idCromoRemitente = data["idCromoRemitente"] as String
                        val estado = data["estado"] as String

                        val intercambio = Intercambios(
                            idUserEmisor = idUserEmisor,
                            idUserRemitente = idUserRemitente,
                            idCromoEmisor = idCromoEmisor,
                            idCromoRemitente = idCromoRemitente,
                            estado = estado
                        )

                        intercambiosRecibidos.add(intercambio)
                        cromoIds.add(idCromoEmisor)
                        cromoIds.add(idCromoRemitente)
                    }
                }
            } catch (e: Exception) {
                Log.e("InterCromo", "Error al obtener intercambios: ${e.localizedMessage}")
            }
        }
        return Pair(intercambiosRecibidos, cromoIds)
    }
}
