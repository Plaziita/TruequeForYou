package com.example.intercromo.presentation.uploadcromo


import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.intercromo.presentation.editarCromo.EditarCromoViewModel
import kotlinx.coroutines.launch

@Composable
fun EditarCromoScreen(viewModel: EditarCromoViewModel,controller: NavController){
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val cromoId: String? = navBackStackEntry?.arguments?.getString("cromoId")
    val descripcionInicial: String? = navBackStackEntry?.arguments?.getString("descripcion")
    val nombreInicial: String? = navBackStackEntry?.arguments?.getString("nombre")
    val categoriaInicial: String? = navBackStackEntry?.arguments?.getString("categoria")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        recogidaDatos(viewModel,cromoId,controller,descripcionInicial,nombreInicial,categoriaInicial)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun recogidaDatos(viewModel: EditarCromoViewModel,cromoId: String?,controller: NavController,descripcionInicial: String?,nombreInicial: String?,categoriaInicial:String?){

    val nombre = viewModel.nombre.value
    val descripcion = viewModel.descripcion.value
    val selectedtText = viewModel.selectedText.value

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = nombre,
            onValueChange = { viewModel.uploadCromoChanged(selectedtText,it, descripcion) },
            label = { Text("Nombre del cromo", color = Color.Black) },
            placeholder = {
                if (nombreInicial != null) {
                    Text(text = nombreInicial, color = Color.Black)
                }
            },
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            leadingIcon = { Icon(Icons.Default.ContactPage, contentDescription = null) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        )
        CategoriaDropDown(selectedtText, nombre, descripcion, viewModel,categoriaInicial)
        OutlinedTextField(
            value = descripcion,
            onValueChange = { viewModel.uploadCromoChanged(selectedtText,nombre,it)},
            label = { Text("Descripcion", color = Color.Black) },
            placeholder = {
                if (descripcionInicial != null) {
                    Text(text = descripcionInicial, color = Color.Black)
                }
            },
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .height(120.dp),
            leadingIcon = { Icon(Icons.Default.Description, contentDescription = null) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        )
        BotonGuardarCromo(nombre, descripcion, selectedtText, cromoId, viewModel, controller)
    }
}

@Composable
fun BotonGuardarCromo(
    nombre: String,
    descripcion: String,
    categoria: String,
    cromoId: String?,
    viewModel: EditarCromoViewModel,
    controller: NavController
) {
    val context = LocalContext.current
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
            onClick = {
                val resultado = viewModel.updateCromo(cromoId,nombre, descripcion, categoria)
                scope.launch {
                            if (resultado) {
                                Toast.makeText(context, "Cromo actualizado correctamente", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Error al actualizar el cromo", Toast.LENGTH_SHORT).show()
                            }
                    controller.popBackStack()
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




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriaDropDown(selectedText:String, nombre:String, descripcion:String, viewModel: EditarCromoViewModel,categoriaInicial: String?) {

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
            placeholder = {
                if (categoriaInicial != null) {
                    Text(text = categoriaInicial,color = Color.Black)
                }
            },
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

