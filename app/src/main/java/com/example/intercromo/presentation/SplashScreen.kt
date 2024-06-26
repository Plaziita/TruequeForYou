package com.example.intercromo.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.intercromo.R
import com.example.intercromo.dao.UsuarioRepository
import com.example.intercromo.navigation.ventanasRegistro.VentanasLogIn
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController, usuarioRepository: UsuarioRepository){

    /*LaunchedEffect(key1 = Unit){
        delay(1000)
        usuarioRepository.llevarAlMenu()
    }*/

    LaunchedEffect(key1 = true){
        delay(2000)
        navController.popBackStack()
        navController.navigate(VentanasLogIn.BienvenidosScreen.ruta)
    }
    Splash()
}
@Composable
fun Splash() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}

@Composable
@Preview
fun SplashPreview(){
    Splash()
}
