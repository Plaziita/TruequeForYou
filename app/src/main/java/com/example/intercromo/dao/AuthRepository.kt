package com.example.intercromo.dao

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.intercromo.navigation.manejadorRutas.Rutas
import com.example.intercromo.navigation.ventanasRegistro.VentanasLogIn
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class AuthRepository(navController: NavController) {
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

    fun registerEmailPassword(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful){
                Log.d("InterCromo", "Registro realizado con éxito")
                navegar.navigate(VentanasLogIn.BienvenidosScreen.ruta)
            }else{
                Log.d("InterCromo", "Error al registrarse")
            }
        }
    }
}