package com.example.intercromo.dao

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class AuthGoogleRepository: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)

    val currentUser: FirebaseUser?
        get() = auth.currentUser

    fun getUserProfileImageUrl(): String? {
        return currentUser?.photoUrl?.toString()
    }
    fun signInWithGoogleCredential(credential: AuthCredential, home:() -> Unit)
            = viewModelScope.launch {
        try{
            auth.signInWithCredential(credential)
                .addOnCompleteListener{ task->
                    if (task.isSuccessful) {
                        Log.d("InterCromo", "Logueado con Google exitoso!")
                        home()
                    }
                }
                .addOnFailureListener {
                    Log.d("InterCromo", "Fallo al registrarse!")
                }
        }
        catch (ex: Exception){
            Log.d("InterCromo", "Excepcion al loguear con Google: " + "${ex.localizedMessage}")
        }
    }
}