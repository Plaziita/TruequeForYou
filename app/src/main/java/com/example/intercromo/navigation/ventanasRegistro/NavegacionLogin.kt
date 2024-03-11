package com.example.intercromo.navigation.ventanasRegistro

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.intercromo.navigation.manejadorRutas.Rutas
import com.example.intercromo.screens.BienvenidoScreen
import com.example.intercromo.screens.EmailScreen
import com.example.intercromo.screens.LoginEmailScreen
import com.example.intercromo.screens.SplashScreen

fun NavGraphBuilder.NavegacionLogin (navController: NavHostController){

    navigation(
        route = Rutas.REGISTRO,
        startDestination = VentanasLogIn.SplashScreen.ruta
    ){
        composable(route = VentanasLogIn.SplashScreen.ruta) {
            SplashScreen(navController)
        }
        composable(route = VentanasLogIn.BienvenidosScreen.ruta) {
            BienvenidoScreen(navController)
        }
        composable(route = VentanasLogIn.EmailScreen.ruta) {
            EmailScreen(navController)
        }
        composable(route = VentanasLogIn.LoginEmailScreen.ruta) {
            LoginEmailScreen(navController)
        }

    }
}