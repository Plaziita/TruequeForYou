package com.example.intercromo.navigation.ventanasRegistro

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.intercromo.dao.UsuarioRepository
import com.example.intercromo.navigation.manejadorRutas.Rutas
import com.example.intercromo.presentation.BienvenidoScreen
import com.example.intercromo.presentation.registro.EmailScreen
import com.example.intercromo.presentation.login.LoginEmailScreen
import com.example.intercromo.presentation.SplashScreen
import com.example.intercromo.presentation.login.LoginViewModel
import com.example.intercromo.presentation.registro.RegistroEmailViewModel

fun NavGraphBuilder.NavegacionLogin (navController: NavHostController){

    val usuarioRepository = UsuarioRepository(navController)
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
            val registroviewmodel = RegistroEmailViewModel(usuarioRepository)
            EmailScreen(registroviewmodel)
        }
        composable(route = VentanasLogIn.LoginEmailScreen.ruta) {
            val loginviewModel= LoginViewModel(usuarioRepository)

            LoginEmailScreen(loginviewModel)
        }

    }
}