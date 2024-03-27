package com.example.intercromo.navigation.rutaPerfil

import PantallaTransferencias
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.dao.UsuarioRepository
import com.example.intercromo.navigation.manejadorRutas.Rutas
import com.example.intercromo.presentation.login.LoginEmailScreen
import com.example.intercromo.presentation.login.LoginViewModel
import com.example.intercromo.presentation.perfil.adquisiciones.PantallaAdquisiciones
import com.example.intercromo.presentation.perfil.PantallaHistorial
import com.example.intercromo.presentation.perfil.PantallaPerfil
import com.example.intercromo.presentation.perfil.adquisiciones.AdquisicionesViewModel

fun NavGraphBuilder.NavegacionPerfil(navController: NavHostController,controllerOpciones: NavHostController){
    val cromoRepository = CromoRepository()

    navigation(
        route = Rutas.PERFIL,
        startDestination = VentanasPerfil.PerfilScreen.ruta
    ){
        composable(route = VentanasPerfil.AdquisicionesScreen.ruta) {
            val viewModel = AdquisicionesViewModel(cromoRepository)

            PantallaAdquisiciones(controllerOpciones,viewModel)
        }
        composable(route = VentanasPerfil.HistorialScreen.ruta) {
            PantallaHistorial(controllerOpciones)
        }
        composable(route = VentanasPerfil.TransferenciasScreen.ruta) {
            PantallaTransferencias(controllerOpciones)
        }
        composable(route = VentanasPerfil.PerfilScreen.ruta) {
            PantallaPerfil(navController)
        }
    }
}