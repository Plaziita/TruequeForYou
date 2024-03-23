package com.example.intercromo.presentation.login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.intercromo.dao.UsuarioRepository

class LoginViewModel(private val usuarioRepository: UsuarioRepository) : ViewModel() {

    /* para que solo se pueda modificar desde aqui pero se vaua actualizando la vista*/
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    fun onLoginChanged(email: String, password: String){
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private  fun isValidPassword(password:String): Boolean{
        return password.length > 5
    }


    fun login(email: String, password: String) {

        usuarioRepository.signInEmailPassword(email, password)

    }
}