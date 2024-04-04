package com.example.intercromo.presentation.uploadcromo

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@Composable
fun UploadCromoScreen(){
Column(
    modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
) {
    recogidaDatos()
}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun recogidaDatos(){

    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        SubirImagen()
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del cromo", color = Color.Black) },
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            leadingIcon = { Icon(Icons.Default.ContactPage, contentDescription = null) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        )
        CategoriaDropDown()
        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripcion", color = Color.Black) },
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .height(120.dp),
            leadingIcon = { Icon(Icons.Default.Description, contentDescription = null) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        )
        BotonGuardarCromo()
    }
}

@Composable
fun SubirImagen(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        IconButton(
            onClick = {},
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
        IconButton(
            onClick = {},
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
    }
}

@Composable
fun BotonGuardarCromo(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        androidx.compose.material3.Button(
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFA500),
                contentColor = Color.Black
            ),
            onClick = {
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriaDropDown() {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Pokemon", "Magic", "Adrenalin", "Otro")
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column {
        // TextField
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
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
                    selectedText = label
                    expanded = false
                })
                {
                    Text(text = label, color = Color.Black)
                }
            }
        }
    }
}
@Composable
@Preview
fun UploadCromoPreview(){
    UploadCromoScreen()
}

