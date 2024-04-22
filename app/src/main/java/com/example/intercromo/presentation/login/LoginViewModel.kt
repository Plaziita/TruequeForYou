package com.example.intercromo.presentation.login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.intercromo.dao.UsuarioRepository
import com.example.intercromo.model.Cromo

class LoginViewModel(private val usuarioRepository: UsuarioRepository) : ViewModel() {

    /* para que solo se pueda modificar desde aqui pero se vaua actualizando la vista*/

    val email: MutableState<String> = mutableStateOf("")

    val password: MutableState<String> = mutableStateOf("")


    val loginEnable: MutableState<Boolean> = mutableStateOf(false)

    fun onLoginChanged(emailp: String, passwordp: String){
        email.value = emailp
        password.value = passwordp
        loginEnable.value = isValidEmail(emailp) && isValidPassword(passwordp)
    }

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private  fun isValidPassword(password:String): Boolean{
        return password.length > 5
    }


    fun login(email: String, password: String) {

        usuarioRepository.signInEmailPassword(email, password)

    }
}