package com.example.intercromo.dao

import android.util.Log
import com.example.intercromo.model.Cromo
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
                val cromoId = document.getString(CAMPO_ID) ?: ""

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
        return try {
            val querySnapshot = cromos.whereEqualTo(CAMPO_FAVORITO, true).get().await()
            val listaCromos = mutableListOf<Cromo>()
            for (document in querySnapshot) {
                val nombre = document.getString(CAMPO_NOMBRE) ?: ""
                val descripcion = document.getString(CAMPO_DESCRIPCION) ?: ""
                val imagen = document.getString(CAMPO_IMAGEN) ?: ""
                val categoria = document.getString(CAMPO_CATEGORIA) ?: ""
                val nombreUsuario = document.getString(CAMPO_NOMBREUSUARIO) ?: ""
                val cromoId = document.getString(CAMPO_ID) ?: ""

                val cromo = Cromo(
                    nombre,
                    descripcion,
                    imagen,
                    categoria,
                    nombreUsuario,
                    true,// Indicamos que el cromo es favorito
                    cromoId
                )

                listaCromos.add(cromo)
                Log.i(TAG, "------> Favorito: $nombre")
            }
            listaCromos
        } catch (e: Exception) {
            // Manejar cualquier error que ocurra al obtener los datos
            Log.e(TAG, "Error al obtener la lista de cromos favoritos", e)
            emptyList()
        }
    }

    fun getCromo(nombre_: String?): Cromo? {
        var cromoDevolver: Cromo? = null

        // Crear un nuevo alcance de corrutina
        CoroutineScope(Dispatchers.IO).launch {
            // Dentro de la corrutina, llamamos a la función suspendida getCromos()
            val cromos = getCromos()

            // Procesamos la lista de cromos
            for (cromo in cromos) {
                if (cromo.nombre.equals(nombre_)) {
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

    fun addCromo(nombre: String, descripcion: String, imagen: String, categoria: String) {
        val userId = Firebase.auth.currentUser?.uid
        val nombre = nombre
        val descripcion = descripcion
        val imagen = imagen
        val categoria = categoria

        val cromo = Cromo(nombre, descripcion, imagen, categoria, userId.toString(), false, null)



        FirebaseFirestore.getInstance().collection("cromos").add(cromo.toMap())
            .addOnSuccessListener {
                it.update("cromoId", it.id)
                Log.d("InterCromo", "Creado")
            }.addOnFailureListener {
                Log.d("InterCromo", "Error")
            }

    }

    suspend fun getCromosAsociados(): List<Cromo> {
        val userId = Firebase.auth.currentUser?.uid
        var listaMisCromos = mutableListOf<Cromo>()

        // Crear un nuevo alcance de corrutina

        // Dentro de la corrutina, llamamos a la función suspendida getCromos()
        val cromos = getCromos()

        // Procesamos la lista de cromos
        for (cromo in cromos) {
            if (cromo.idUsuario.equals(userId)) {
                listaMisCromos.add(cromo)
            }
        }
        return listaMisCromos
    }

    // Esperamos hasta que la corrutina termine de ejecutarse


}



