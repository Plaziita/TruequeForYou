package com.example.intercromo.presentation.perfil.modificarPerfil

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intercromo.dao.UsuarioRepository
import kotlinx.coroutines.launch

class ModificarViewModel(private val usuarioRepository: UsuarioRepository) : ViewModel() {

    /* para que solo se pueda modificar desde aqui pero se vaua actualizando la vista*/

    //val nombre: MutableState<String> = mutableStateOf("")
    //val password: MutableState<String> = mutableStateOf("")
    val nombre = mutableStateOf("")
    val password = mutableStateOf("")
    val userProfileImageUrl = mutableStateOf<String?>(null)
    val mostrarMensajeExito = mutableStateOf(false)


    fun cambiarNombre(nuevoNombre: String) {
        nombre.value = nuevoNombre
    }
    fun cambiarPassword(nuevaPassword: String) {
        password.value = nuevaPassword
    }
    fun guardarCambios(nombre: String, password: String) {
        viewModelScope.launch {
            try {
                usuarioRepository.actualizarNombreDeUsuario(nombre)
                usuarioRepository.actualizarPassword(password)
                mostrarMensajeExito.value = true
            } catch (e: Exception) {
                // Manejo de errores, por ejemplo, mostrar un mensaje de error
                e.printStackTrace()
            }
        }
    }
    /*fun cambiarPassword(password: String){
        return usuarioRepository.cambiarContraseña(password)
    }*/

    /*fun cambiarNombre(nombre: String){
        return usuarioRepository.cambiarNombre(nombre)
    }*/

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private  fun isValidPassword(password:String): Boolean{
        return password.length > 5
    }


    fun login(email: String, password: String) {

        usuarioRepository.signInEmailPassword(email, password)

    }
}