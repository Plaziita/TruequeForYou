package com.example.intercromo.presentation.uploadcromo

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.ContactPage
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID

@Composable
fun UploadCromoScreen(viewModel: UploadCromoViewModel){
Column(
    modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
) {
    recogidaDatos(viewModel)
}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun recogidaDatos(viewModel: UploadCromoViewModel){

    val nombre = viewModel.nombre.value
    val descripcion = viewModel.descripcion.value
    val selectedtText = viewModel.selectedText.value
    val estadoBoton:Boolean = viewModel.upLoadEnable.value

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        val imagen = SubirImagen1()
        OutlinedTextField(
            value = nombre,
            onValueChange = { viewModel.uploadCromoChanged(selectedtText,it, descripcion) },
            label = { Text("Nombre del cromo", color = Color.Black) },
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            leadingIcon = { Icon(Icons.Default.ContactPage, contentDescription = null) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        )
        CategoriaDropDown(selectedtText, nombre, descripcion, viewModel)
        OutlinedTextField(
            value = descripcion,
            onValueChange = { viewModel.uploadCromoChanged(selectedtText,nombre,it)},
            label = { Text("Descripcion", color = Color.Black) },
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .height(120.dp),
            leadingIcon = { Icon(Icons.Default.Description, contentDescription = null) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        )
        BotonGuardarCromo(estadoBoton, nombre, descripcion, imagen, selectedtText, viewModel)
    }
}
@Composable
fun SubirImagen1(): Uri?{
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            bitmap.value?.let { btm ->
                Image(
                    bitmap = btm.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                        .height(120.dp)
                        .border(
                            border = BorderStroke(2.dp, Color(0xFFFFA500)),
                            shape = MaterialTheme.shapes.small
                        ),
                )
            }
        }
        IconButton(
            onClick = {launcher.launch("image/*") },
            modifier = Modifier
                .weight(1f)
                .height(120.dp)
                .border(
                    border = BorderStroke(2.dp, Color(0xFFFFA500)),
                    shape = MaterialTheme.shapes.small
                ),
            content = {
                Icon(Icons.Default.PhotoCamera, contentDescription = null)
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
    }

    return  imageUri
}


@Composable
fun BotonGuardarCromo(
    estadoBoton: Boolean,
    nombre: String,
    descripcion: String,
    imagen: Uri?,
    categoria: String,
    viewModel: UploadCromoViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val scope = rememberCoroutineScope()

        androidx.compose.material3.Button(
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFA500),
                contentColor = Color.Black
            ),
            enabled = estadoBoton,
            onClick = {
                imagen?.let { uri ->
                    scope.launch {
                        val imageUrl = convertirUriAUrl(uri)
                        imageUrl?.let {
                            viewModel.addCromo(nombre, descripcion, it, categoria)
                        }
                    }
                }
            }
        ) {
            Text(
                text = "Subir cromo",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.ArrowUpward,
                contentDescription = "Navigation Icon",
                tint = Color.Black
            )
        }
    }
}

private suspend fun convertirUriAUrl(uri: Uri): String? {
    return try {
        val storageRef = Firebase.storage.reference
        val imageRef = storageRef.child("images/${UUID.randomUUID()}.jpg")

        // Subir la imagen a Firebase Storage
        imageRef.putFile(uri).await()

        // Obtener la URL de la imagen
        val downloadUrl = imageRef.downloadUrl.await()

        downloadUrl.toString()
    } catch (e: Exception) {
        Log.e("UploadCromoViewModel", "Error al subir la imagen a Firebase Storage: ${e.message}", e)
        null // Retorna null si hay un error
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriaDropDown(selectedText:String, nombre:String, descripcion:String, viewModel: UploadCromoViewModel) {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Pokemon", "Magic", "Adrenalin", "Otro")


    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column {
        // TextField
        OutlinedTextField(
            value = selectedText,
            onValueChange = { viewModel.uploadCromoChanged(it,nombre,descripcion)},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()
                },
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
            label = {Text("Categoria", color = Color.Black)},
            leadingIcon = { Icon(Icons.Default.Category, contentDescription = null) },
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )

        // DropdownMenu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    viewModel.labelSelectedText(label)
                    expanded = false
                })
                {
                    Text(text = label, color = Color.Black)
                }
            }
        }
    }
}

