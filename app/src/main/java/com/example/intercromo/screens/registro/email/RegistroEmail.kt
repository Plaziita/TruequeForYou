package com.example.intercromo.screens.registro.email

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
fun EmailScreen(navController: NavController) {
    val navController = navController
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
            datosInicio(navController)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun datosInicio(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nombreApellidos by remember { mutableStateOf("") }
    var estadoBoton by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    val auth = UsuarioRepository(navController)

    OutlinedTextField(
        value = nombreApellidos,
        onValueChange = { nombreApellidos = it },
        label = { Text("Nombre y apellidos") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        leadingIcon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
    )

    OutlinedTextField(
        value = email,
        onValueChange = { email = it },
        label = { Text("Correo electrónico") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),

        )

    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Contraseña") },
        modifier = Modifier
            .fillMaxWidth(),
        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
    )

    Spacer(modifier = Modifier.height(16.dp))

    if (email.isNotEmpty() && password.isNotEmpty() && nombreApellidos.isNotEmpty()) {
        estadoBoton = true
    }

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
            if (email.isNotEmpty() && password.isNotEmpty()) {

                auth.registerEmailPassword(email, password, nombreApellidos)

            }

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

@Composable
@Preview
fun EmailPreview() {
    EmailScreen(rememberNavController())
}