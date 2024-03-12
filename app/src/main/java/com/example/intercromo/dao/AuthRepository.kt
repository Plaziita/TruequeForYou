package com.example.intercromo.dao

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.intercromo.model.Usuario
import com.example.intercromo.navigation.manejadorRutas.Rutas
import com.example.intercromo.navigation.ventanasRegistro.VentanasLogIn
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class AuthRepository(navController: NavController): ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val loading = MutableLiveData(false)/*Para que no se introduzcan dos iguales */
    private val navegar = navController

    fun signInEmailPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("InterCromo", "Logueado con éxito")
                navegar.navigate(Rutas.BARRANAVEGACION)
            } else{
                Log.d("InterCromo", "Error al loguearse")
            }
        }
    }

    fun registerEmailPassword(email: String, password: String, name:String){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful){
                val nombreEmail = it.result.user?.email?.split("@")?.get(0)
                createUser(name, nombreEmail.toString())
                Log.d("InterCromo", "Registro realizado con éxito")
                navegar.navigate(VentanasLogIn.BienvenidosScreen.ruta)
            }else{
                Log.d("InterCromo", "Error al registrarse")
            }
        }
    }

    fun createUser(name:String, email: String){
        val userId = auth.currentUser?.uid
        val name = name
        val email = email

        val user = Usuario(name, email, userId.toString(), null, null, 0.0)

        FirebaseFirestore.getInstance().collection("usuarios").add(user.toMap()).addOnSuccessListener {
            Log.d("InterCromo", "Creado")
        }.addOnFailureListener {
            Log.d("InterCromo", "Error")
        }
    }


}