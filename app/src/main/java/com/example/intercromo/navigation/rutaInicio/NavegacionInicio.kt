package com.example.intercromo.navigation.rutaInicio

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.navigation.manejadorRutas.Rutas
import com.example.intercromo.navigation.rutaPerfil.VentanasPerfil
import com.example.intercromo.presentation.PantallaCromo

fun NavGraphBuilder.NavegacionInicio(controllerOpciones: NavHostController){

    val cromorepository = CromoRepository()

    navigation(
        route = Rutas.INICIO,
        startDestination = VentanasPerfil.PerfilScreen.ruta
    ){
        composable(route = VentanasPerfil.AdquisicionesScreen.ruta) {
            PantallaCromo()
        }
    }
}