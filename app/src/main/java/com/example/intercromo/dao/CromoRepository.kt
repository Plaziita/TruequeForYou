package com.example.intercromo.dao

import android.util.Log
import com.example.intercromo.model.Cromo
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class CromoRepository {

    private val TAG = "DataSource_Cromo"

    private val COLECCION_CROMOS = "cromos"
    private val CAMPO_NOMBRE = "nombre"
    private val CAMPO_IMAGEN = "imagen"
    private val CAMPO_DESCRIPCION = "descripcion"
    private val CAMPO_CATEGORIA = "categoria"
    private val CAMPO_NOMBREUSUARIO = "id_usuario"
    private val CAMPO_FAVORITO = "favorito"
    private val CAMPO_ID = "cromoId"

    private val db = FirebaseFirestore.getInstance()
    private val cromos = db.collection(COLECCION_CROMOS)
    private val auth: FirebaseAuth = Firebase.auth

    suspend fun getCromos(): List<Cromo> {
        return try {
            val querySnapshot = cromos.get().await()
            val listaCromos = mutableListOf<Cromo>()
            for (document in querySnapshot) {
                val nombre = document.getString(CAMPO_NOMBRE) ?: ""
                val descripcion = document.getString(CAMPO_DESCRIPCION) ?: ""
                val imagen = document.getString(CAMPO_IMAGEN) ?: ""
                val categoria = document.getString(CAMPO_CATEGORIA) ?: ""
                val nombreUsuario = document.getString(CAMPO_NOMBREUSUARIO) ?: ""
                val favorito = document.getBoolean(CAMPO_FAVORITO) ?: false
                val cromoId = document.getString(CAMPO_ID)  ?:""

                val cromo = Cromo(
                    nombre,
                    descripcion,
                    imagen,
                    categoria,
                    nombreUsuario,
                    favorito,
                    cromoId
                )

                listaCromos.add(cromo)
                Log.i(TAG, "------> Cromo: $nombre")
            }
            listaCromos
        } catch (e: Exception) {
            // Manejar cualquier error que ocurra al obtener los datos
            Log.e(TAG, "Error al obtener la lista de cromos", e)
            emptyList()
        }
    }


    suspend fun getFavoritos(): List<Cromo> {

            val user = auth.currentUser
            if (user != null) {
                val userId = user.uid
                val firestore = FirebaseFirestore.getInstance()
                val userDocRef = firestore.collection("usuarios").document(userId)

                try {
                    val documentSnapshot = userDocRef.get().await()
                    val favoritos = documentSnapshot.get("cromosFavoritos") as? List<String> ?: emptyList()

                    val favoritosList = mutableListOf<Cromo>()
                    for (cromoId in favoritos) {
                        val cromoDocRef = firestore.collection("cromos").document(cromoId)
                        val cromoSnapshot = cromoDocRef.get().await()
                        val cromo = cromoSnapshot.toObject<Cromo>()
                        if (cromo != null) {
                            favoritosList.add(cromo)
                        }
                    }
                    return favoritosList
                } catch (e: Exception) {
                    Log.d("InterCromo", "Error obteniendo favoritos: $e")
                }
            } else {
                Log.d("InterCromo", "Usuario no autenticado.")
            }
            return emptyList()
        }


    fun getCromo(cromoId: String?): Cromo? {
        var cromoDevolver: Cromo? = null

        // Crear un nuevo alcance de corrutina
        CoroutineScope(Dispatchers.IO).launch {
            // Dentro de la corrutina, llamamos a la función suspendida getCromos()
            val cromos = getCromos()

            // Procesamos la lista de cromos
            for (cromo in cromos) {
                if (cromo.cromoId.equals(cromoId)) {
                    cromoDevolver = cromo
                    break
                }
            }
        }

        // Esperamos hasta que la corrutina termine de ejecutarse
        runBlocking {
            delay(1000) // Agregamos un retardo para esperar que la corrutina termine (esto es opcional)
        }

        return cromoDevolver
    }
    fun addCromo(nombre: String, descripcion: String, imagen:String, categoria:String) {
        val userId = Firebase.auth.currentUser?.uid
        val nombre = nombre
        val descripcion = descripcion
        val imagen = imagen
        val categoria = categoria

        val cromo = Cromo(nombre, descripcion, imagen, categoria, userId.toString(), false, "")



        FirebaseFirestore.getInstance().collection("cromos").add(cromo.toMap())
            .addOnSuccessListener {
                it.update("cromoId", it.id)
                Log.d("InterCromo", "Creado")
            }.addOnFailureListener {
                Log.d("InterCromo", "Error")
            }

    }

    fun getCromosAsociados(): MutableList<Cromo> {
        val userId = Firebase.auth.currentUser?.uid
        var listaMisCromos = mutableListOf<Cromo>()

        // Crear un nuevo alcance de corrutina
        CoroutineScope(Dispatchers.IO).launch {
            // Dentro de la corrutina, llamamos a la función suspendida getCromos()
            val cromos = getCromos()

            // Procesamos la lista de cromos
            for (cromo in cromos) {
                if (cromo.idUsuario == userId) {
                    listaMisCromos.add(cromo)
                }
            }
        }

        // Esperamos hasta que la corrutina termine de ejecutarse
        runBlocking {
            delay(1000) // Agregamos un retardo para esperar que la corrutina termine (esto es opcional)
        }

        return listaMisCromos

    }

    fun getCromosFiltrados(query: String): List<Cromo> = runBlocking {
        val listaCromos = async(Dispatchers.IO) {
            getCromos()
        }.await()

        if (query.isEmpty()) {
            emptyList()
        } else {
            listaCromos.filter { cromo ->
                cromo.nombre.contains(query, ignoreCase = true) || cromo.categoria.contains(query, ignoreCase = true)
            }
        }
    }

    suspend fun deleteCromo(cromoId: String?) {
        try {
            if (cromoId != null) {
                FirebaseFirestore.getInstance()
                    .collection("cromos")
                    .document(cromoId)
                    .delete()
                    .await()
            }
            Log.d("InterCromo", "Cromo eliminado exitosamente")
        } catch (e: Exception) {
            Log.e("InterCromo", "Error al eliminar el cromo: ${e.message}", e)
        }
    }

     fun updateCromo(cromoId: String?, nombre: String, descripcion: String, categoria: String) :Boolean {
        try {
            val data = hashMapOf(
                "nombre" to nombre,
                "descripcion" to descripcion,
                "categoria" to categoria
            ).toMutableMap() as MutableMap<String, Any>
            if (cromoId != null) {
                FirebaseFirestore.getInstance()
                    .collection("cromos")
                    .document(cromoId)
                    .update(data)
            }
            return true
            Log.d("InterCromo", "Cromo actualizado exitosamente")
        } catch (e: Exception) {
            return false
            Log.e("InterCromo", "Error al actualizar el cromo: ${e.message}", e)
        }
    }




}