package com.example.intercromo.presentation.perfil.modificarPerfil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.intercromo.R
import com.example.intercromo.dao.UsuarioRepository
import kotlinx.coroutines.launch
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModificarPerfilScreen(navController: NavController,viewModel: ModificarViewModel){

    val auth = UsuarioRepository(navController)
    val password: String = viewModel.password.value
    val nombre: String = viewModel.nombre.value
    val userProfileImageUrl = viewModel.userProfileImageUrl.value
    val mostrarMensajeExito = viewModel.mostrarMensajeExito.value
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Navigation Icon",
                tint = Color.Black,
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    }
                    .size(35.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Modificar perfil",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.size(100.dp)) {
                if (userProfileImageUrl != null) {
                    Image(
                        painter = rememberImagePainter(userProfileImageUrl),
                        contentDescription = "Imagen del usuario",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(100.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Imagen predeterminada",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(100.dp)
                            .clip(CircleShape)
                    )
                }
                Text(
                    text = "Cambiar imagen",
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 8.dp)
                        .background(Color.Black.copy(alpha = 0.5f))
                        .padding(horizontal = 4.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = { viewModel.cambiarNombre(it) },
            label = { Text("Nombre", color = Color.Black) },
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth(),
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        )

        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.cambiarPassword(it) },
            label = { Text("Contraseña", color = Color.Black) },
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth(),
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier
                    .alpha(0.8f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFA500),
                    contentColor = Color.Black
                ),
                onClick = {
                    scope.launch {
                        viewModel.guardarCambios(nombre, password)
                    }
                }
            ) {
                Text(
                    text = "Guardar cambios",
                    fontSize = 18.sp
                )
            }
        }

        // Snackbar para mostrar el mensaje de éxito
        if (mostrarMensajeExito) {
            Snackbar(
                modifier = Modifier.padding(16.dp),
                content = { Text(text = "¡Cambio realizado con éxito!") },
                action = {
                    IconButton(onClick = { viewModel.mostrarMensajeExito.value = false }) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar")
                    }
                }
            )
        }
    }
}