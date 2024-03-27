package com.example.intercromo.navigation.rutaPerfil

import PantallaIntercambios
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.intercromo.navigation.manejadorRutas.Rutas
import com.example.intercromo.presentation.perfil.PantallaHistorial
import com.example.intercromo.presentation.perfil.PantallaPerfil

fun NavGraphBuilder.NavegacionPerfil(navController: NavHostController,controllerOpciones: NavHostController){

    navigation(
        route = Rutas.PERFIL,
        startDestination = VentanasPerfil.PerfilScreen.ruta
    ){
        composable(route = VentanasPerfil.AdquisicionesScreen.ruta) {
           // PantallaAdquisiciones(controllerOpciones,viewModel)
        }
        composable(route = VentanasPerfil.HistorialScreen.ruta) {
            PantallaHistorial(controllerOpciones)
        }
        composable(route = VentanasPerfil.TransferenciasScreen.ruta) {
            PantallaIntercambios(controllerOpciones)
        }
        composable(route = VentanasPerfil.PerfilScreen.ruta) {
            PantallaPerfil(navController)
        }
    }
}