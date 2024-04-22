package com.example.intercromo.presentation.registro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.intercromo.dao.UsuarioRepository

@Composable
fun EmailScreen(viewModel: RegistroEmailViewModel) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Empieza tu aventura!",
                fontSize = 30.sp,
                color = Color(0xFFFFA500),
            )
            Spacer(modifier = Modifier.height(16.dp))
            datosInicio(viewModel)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun datosInicio(viewModel: RegistroEmailViewModel) {

    var email = viewModel.email.value
    var password = viewModel.password.value
    var nombreApellidos = viewModel.nombre.value
    var estadoBoton = viewModel.registrarEnable.value
    var showDialog by remember { mutableStateOf(false) }



    OutlinedTextField(
        value = nombreApellidos,
        onValueChange = { viewModel.onRegistroChanged(email, password, it)},
        label = { Text("Nombre y apellidos", color = Color.Black) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
        leadingIcon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
    )

    OutlinedTextField(
        value = email,
        onValueChange = { viewModel.onRegistroChanged(it, password, nombreApellidos) },
        label = { Text("Correo electrónico", color = Color.Black) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),

        )

    OutlinedTextField(
        value = password,
        onValueChange = { viewModel.onRegistroChanged(email, it, nombreApellidos)},
        label = { Text("Contraseña", color = Color.Black) },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
    )

    Spacer(modifier = Modifier.height(16.dp))


    Button(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(0.8f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFFA500),
            contentColor = Color.Black
        ),
        enabled = estadoBoton,
        onClick = {
            viewModel.registrar(email, password, nombreApellidos)

        }
    ) {
        Text(
            fontSize = 24.sp,
            text = "Crear cuenta",
        )
    }

    /*
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = {
                Text(text = "Registro con éxito!")
            },
            text = {
                Text(text = "Usuario con email: $email , registrado con éxito!")
            },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    navController.navigate(VentanasLogIn.BienvenidosScreen.ruta)
                }) {
                    Text(text = "Aceptar")
                }
            }
        )
    }

     */


}

