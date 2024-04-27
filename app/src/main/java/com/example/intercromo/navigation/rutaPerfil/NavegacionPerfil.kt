package com.example.intercromo.navigation.rutaPerfil

import PantallaIntercambios
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.dao.UsuarioRepository
import com.example.intercromo.navigation.manejadorRutas.Rutas
import com.example.intercromo.presentation.perfil.PantallaHistorial
import com.example.intercromo.presentation.perfil.PantallaPerfil
import com.example.intercromo.presentation.perfil.misCromos.MisCromosViewModel
import com.example.intercromo.presentation.perfil.misCromos.PantallaMisCromos
import com.example.intercromo.presentation.perfil.modificarPerfil.ModificarPerfilScreen
import com.example.intercromo.presentation.perfil.modificarPerfil.ModificarViewModel

fun NavGraphBuilder.NavegacionPerfil(controllerOpciones: NavHostController){


    val cromorepository = CromoRepository()
    val usuarioRepository = UsuarioRepository(controllerOpciones)

    navigation(
        route = Rutas.PERFIL,
        startDestination = VentanasPerfil.PerfilScreen.ruta
    ){
        composable(route = VentanasPerfil.AdquisicionesScreen.ruta) {
            val viewmodelAdquisiciones = MisCromosViewModel(cromorepository)
           PantallaMisCromos(controllerOpciones,viewmodelAdquisiciones)
        }
        composable(route = VentanasPerfil.HistorialScreen.ruta) {
            PantallaHistorial(controllerOpciones)
        }
        composable(route = VentanasPerfil.TransferenciasScreen.ruta) {
            PantallaIntercambios(controllerOpciones)
        }
        composable(route = VentanasPerfil.ModificarScreen.ruta){
            val viewmodelModificar = ModificarViewModel(usuarioRepository)
            ModificarPerfilScreen(controllerOpciones, viewmodelModificar)
        }
        composable(route = VentanasPerfil.PerfilScreen.ruta) {
            PantallaPerfil(controllerOpciones)
        }
    }
}