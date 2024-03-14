package com.example.intercromo.dao

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.intercromo.navigation.manejadorRutas.Rutas
import com.example.intercromo.navigation.ventanasRegistro.VentanasLogIn
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class AuthGoogleRepository(navController: NavController) {
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)
    private val controller = navController

    val currentUser: FirebaseUser?
        get() = auth.currentUser

    fun getUserProfileImageUrl(): String? {
        return currentUser?.photoUrl?.toString()
    }
    fun signInWithGoogleCredential(credential: AuthCredential) {
        try {
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("InterCromo", "Logueado con Google exitoso!")
                        controller.navigate(Rutas.BARRANAVEGACION)
                    }
                }
                .addOnFailureListener {
                    Log.d("InterCromo", "Fallo al registrarse!")
                }
        } catch (ex: Exception) {
            Log.d("InterCromo", "Excepcion al loguear con Google: " + "${ex.localizedMessage}")
        }
    }

    fun cerrarSesion(context: Context){
        try {
            // Cerrar sesión en Firebase
            auth.signOut()

            // Desvincular la cuenta de Google utilizando la API de Google Sign-In
            val googleSignInClient = GoogleSignIn.getClient(context, GoogleSignInOptions.DEFAULT_SIGN_IN)
            googleSignInClient.revokeAccess().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Desvinculación exitosa
                    Log.d("InterCromo", "Google account unlinked")
                } else {
                    // Manejar error
                    Log.e("InterCromo", "Failed to unlink Google account")
                }

                // Navegar a la pantalla de inicio de sesión
                controller.navigate(VentanasLogIn.BienvenidosScreen.ruta)
            }
        } catch (ex: Exception){
            Log.d("InterCromo", "Excepcion al cerrar sesion " + "${ex.localizedMessage}")
        }
    }


}