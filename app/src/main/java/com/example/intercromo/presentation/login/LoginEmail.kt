@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.intercromo.presentation.login

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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginEmailScreen(viewModel: LoginViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Bienvenido de nuevo!",
                fontSize = 30.sp,
                color = Color(0xFFFFA500),
            )
            Spacer(modifier = Modifier.height(16.dp))
            recogidaDatos(viewModel)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun recogidaDatos(viewModel: LoginViewModel) {


    val email:String by viewModel.email.observeAsState(initial = "")
    val password:String by viewModel.password.observeAsState(initial = "")
    val estadoBoton:Boolean by viewModel.loginEnable.observeAsState(initial = false)


    OutlinedTextField(
        value = email,
        onValueChange = {viewModel.onLoginChanged(it , password)},
        label = { Text("Correo electrónico", color = Color.Black) },
        textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),


        )

    OutlinedTextField(
        value = password,
        onValueChange = {viewModel.onLoginChanged(email , it)},
        label = { Text("Contraseña", color = Color.Black) },
        textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),

        modifier = Modifier
            .fillMaxWidth(),
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
            viewModel.login(email, password)
        }
    ) {
        Text(
            fontSize = 24.sp,
            text = "Iniciar sesion",
        )
    }
}



