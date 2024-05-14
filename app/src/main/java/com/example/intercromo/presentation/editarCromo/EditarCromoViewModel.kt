package com.example.intercromo.presentation.editarCromo

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.intercromo.dao.CromoRepository

class EditarCromoViewModel (cromoRepository: CromoRepository): ViewModel(){

    val cromoRepository = cromoRepository

    val selectedText: MutableState<String> = mutableStateOf("")
    val nombre: MutableState<String> = mutableStateOf("")
    val descripcion : MutableState<String> = mutableStateOf("")
    val categoria: MutableState<String> = mutableStateOf("")
    val upLoadEnable: MutableState<Boolean> = mutableStateOf(false)

    fun updateCromo(cromoId: String?, nombre: String, descripcion: String, categoria: String) :Boolean {
          return cromoRepository.updateCromo(cromoId, nombre, descripcion, categoria)
    }

    fun labelSelectedText(selectedText_: String) {
        selectedText.value = selectedText_
    }

    fun isValidText(selectedText_: String): Boolean {
        return selectedText_.length > 0
    }

    fun isValidNombre(nombre_: String): Boolean {
        return nombre_.length > 0
    }

    fun isValidDescripcion(descripcion_: String): Boolean {
        return descripcion_.length > 0
    }

    fun uploadCromoChanged(selectedText_: String, nombre_: String, descripcion_: String) {
        selectedText.value = selectedText_
        nombre.value = nombre_
        descripcion.value = descripcion_
        upLoadEnable.value =
            isValidText(selectedText_) && isValidNombre(nombre_) && isValidDescripcion(descripcion_)
    }
}