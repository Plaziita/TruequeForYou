package com.example.intercromo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter

import com.example.intercromo.dao.AuthGoogleRepository

@Composable
fun PantallaPerfil(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ){
        val navController = rememberNavController()
        ProfileScreen(navController)
    }
}
@Composable
fun ProfileScreen(
navController: NavController
) {
    val authGoogle = AuthGoogleRepository(navController)
    // Obtener el usuario actualmente autenticado
    val currentUser = authGoogle.currentUser
    val userProfileImageUrl = authGoogle.getUserProfileImageUrl()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mostrar los datos del usuario
        Text(
            text = "Perfil de Usuario",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (currentUser != null) {
            if (userProfileImageUrl != null) {
                Image(
                    painter = rememberImagePainter(userProfileImageUrl),
                    contentDescription = "User Profile Image",
                    modifier = Modifier.size(200.dp)
                )
                Text(
                    text = "Nombre: ${currentUser.displayName}",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Email: ${currentUser.email}",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                // Aquí puedes agregar más detalles según lo necesites
            } else {
                Text(
                    text = "Usuario no autenticado",
                )
            }
        }
    }
}


@Composable
@Preview
fun PantallaPerfilPreview(){
    PantallaPerfil()
}