package com.example.intercromo.presentation.registro

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.intercromo.dao.UsuarioRepository

class RegistroEmailViewModel(private val usuarioRepository: UsuarioRepository) : ViewModel() {

    val nombre:MutableState<String> = mutableStateOf("")

    val email:MutableState<String> = mutableStateOf("")

    val password:MutableState<String> = mutableStateOf("")

    val registrarEnable: MutableState<Boolean> = mutableStateOf(false)

    fun onRegistroChanged(emailp: String, passwordp: String, nombrep:String){
        email.value = emailp
        password.value = passwordp
        nombre.value = nombrep
        registrarEnable.value = isValidEmail(emailp) && isValidPassword(passwordp) && isValidNombre(nombrep)
    }

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private  fun isValidPassword(password:String): Boolean{
        return password.length > 5
    }

    private fun isValidNombre(nombre:String):Boolean{
        return !nombre.isBlank()
    }

    fun registrar(email:String, password:String, nombre:String){
        usuarioRepository.registerEmailPassword(email, password, nombre)
    }
}