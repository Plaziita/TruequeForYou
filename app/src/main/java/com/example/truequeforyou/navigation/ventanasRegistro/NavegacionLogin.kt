package com.example.truequeforyou.navigation.ventanasRegistro

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.truequeforyou.screens.BienvenidoScreen
import com.example.truequeforyou.screens.EmailScreen
import com.example.truequeforyou.screens.GmailScreen

@Composable
fun NavegacionLogin (navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = VentanasLogIn.SplashScreen.ruta
    ){
        composable(route = VentanasLogIn.BienvenidosScreen.ruta) {
            BienvenidoScreen()
        }
        composable(route = VentanasLogIn.EmailScreen.ruta) {
            EmailScreen()
        }
        composable(route = VentanasLogIn.GmailScreen.ruta) {
            GmailScreen()
        }

    }
}