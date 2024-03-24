package com.example.intercromo.db

import android.util.Log
import com.example.intercromo.model.Cromo
import com.google.firebase.firestore.FirebaseFirestore

class Datasource_Cromo {

    private val TAG = "DataSource_Cromo"

    private val COLECCION_CROMOS = "cromos"
    private val CAMPO_NOMBRE = "nombre"
    private val CAMPO_IMAGEN = "imagen"
    private val CAMPO_DESCRIPCION = "descripcion"
    private val CAMPO_CATEGORIA = "categoria"
    private val CAMPO_NOMBRE_USUARIO = "nombre_usuario"

    private val db = FirebaseFirestore.getInstance()
    private val cromos = db.collection(COLECCION_CROMOS)

    fun getCromos(setLista: (List<Cromo>) -> Unit) {

        val listaCromos = mutableListOf<Cromo>()
        cromos.get().addOnSuccessListener { documents ->

            for (document in documents) {
                val nombre = document.getString(CAMPO_NOMBRE) ?: ""
                val descripcion = document.getString(CAMPO_DESCRIPCION) ?: ""
                val imagen = document.getString(CAMPO_IMAGEN) ?: ""
                val categoria = document.getString(CAMPO_CATEGORIA) ?: ""
                val nombre_usuario = document.getString(CAMPO_NOMBRE_USUARIO) ?: ""

                val cromo = Cromo(
                    nombre,
                    descripcion,
                    imagen,
                    categoria,
                    nombre_usuario
                )

                listaCromos.add(cromo)

                Log.i(TAG, "------> Cromo: $nombre")

            }

            setLista(listaCromos)

        }

        }


}