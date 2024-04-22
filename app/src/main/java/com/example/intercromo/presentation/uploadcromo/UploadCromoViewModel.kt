package com.example.intercromo.presentation.uploadcromo

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.UUID

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
    fun uploadImageToFirebase(uri: Uri) {
        val storage = Firebase.storage
        val storageRef = storage.reference
        val imagesRef = storageRef.child("images/${UUID.randomUUID()}")

        // Subir la imagen a Firebase Storage
        imagesRef.putFile(uri)
            .addOnSuccessListener {
                // Manejar el éxito de la carga de la imagen
            }
            .addOnFailureListener {
                // Manejar el error de la carga de la imagen
            }
    }

}