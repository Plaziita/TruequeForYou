package com.example.intercromo.presentation.uploadcromo

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.IntSize

class UploadCromoViewModel {

    val selectedText: MutableState<String> = mutableStateOf("")

    val textFieldSize: MutableState<Size> = mutableStateOf(Size.Zero)

    val nombre: MutableState<String> = mutableStateOf("")

    val descripcion: MutableState<String> = mutableStateOf("")

    val upLoadEnable: MutableState<Boolean> = mutableStateOf(false)

    fun uploadCromoChanged(selectedText_: String, nombre_:String, descripcion_:String){
        selectedText.value = selectedText_
        nombre.value = nombre_
        descripcion.value = descripcion_
        upLoadEnable.value = isValidText(selectedText_) && isValidNombre(nombre_) && isValidDescripcion(descripcion_)
    }

    fun labelSelectedText(selectedText_: String){
        selectedText.value = selectedText_
    }

    fun isValidText(selectedText_: String):Boolean{
        return selectedText_.length > 0
    }

    fun isValidNombre(nombre_: String):Boolean{
        return nombre_.length>0
    }

    fun isValidDescripcion(descripcion_: String):Boolean{
        return descripcion_.length>0
    }
}