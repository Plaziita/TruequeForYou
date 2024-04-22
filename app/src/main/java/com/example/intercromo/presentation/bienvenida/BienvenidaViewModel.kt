package com.example.intercromo.presentation.bienvenida

import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModel
import com.example.intercromo.dao.UsuarioRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider

class BienvenidaViewModel(private val usuarioRepository: UsuarioRepository) : ViewModel() {

    fun signGoogle(credential: AuthCredential){
        usuarioRepository.signInWithGoogleCredential(credential)
    }

    fun createGoogleUser(it: ActivityResult): Task<GoogleSignInAccount> {
        return GoogleSignIn.getSignedInAccountFromIntent(it.data)
    }

    fun getCredential(account: GoogleSignInAccount): AuthCredential {
        return GoogleAuthProvider.getCredential(account.idToken, null)
    }

    fun getOpciones(token: String): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        )
            .requestIdToken(token)
            .requestEmail()
            .build()
    }

    fun createClient(opciones: GoogleSignInOptions, context: Context): GoogleSignInClient {
        return GoogleSignIn.getClient(context, opciones)
    }
}