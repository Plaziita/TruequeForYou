package com.example.intercromo.navigation.rutaPerfil

import PantallaIntercambios
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.navigation.manejadorRutas.Rutas
import com.example.intercromo.presentation.perfil.PantallaHistorial
import com.example.intercromo.presentation.perfil.PantallaPerfil
import com.example.intercromo.presentation.perfil.adquisiciones.AdquisicionesViewModel
import com.example.intercromo.presentation.perfil.adquisiciones.PantallaAdquisiciones

fun NavGraphBuilder.NavegacionPerfil(controllerOpciones: NavHostController){


    val cromorepository = CromoRepository()
    navigation(
        route = Rutas.PERFIL,
        startDestination = VentanasPerfil.PerfilScreen.ruta
    ){
        composable(route = VentanasPerfil.AdquisicionesScreen.ruta) {
            val viewmodelAdquisiciones = AdquisicionesViewModel(cromorepository)
           PantallaAdquisiciones(controllerOpciones,viewmodelAdquisiciones)
        }
        composable(route = VentanasPerfil.HistorialScreen.ruta) {
            PantallaHistorial(controllerOpciones)
        }
        composable(route = VentanasPerfil.TransferenciasScreen.ruta) {
            PantallaIntercambios(controllerOpciones)
        }
        composable(route = VentanasPerfil.PerfilScreen.ruta) {
            PantallaPerfil(controllerOpciones)
        }
    }
}