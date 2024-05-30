package com.example.intercromo.navigation.rutaIntercambios

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.dao.IntercambiosRepository
import com.example.intercromo.dao.UsuarioRepository
import com.example.intercromo.navigation.manejadorRutas.Rutas
import com.example.intercromo.presentation.intercambios.seleccionarCarta.PantallaSeleccionarCromo
import com.example.intercromo.presentation.intercambios.seleccionarCarta.SeleccionarIdViewModel

fun NavGraphBuilder.NavegacionIntercambios(controller: NavHostController){

    val intercambiosRepository = IntercambiosRepository(controller)
    val cromorepository = CromoRepository()
    val usuarioRepository = UsuarioRepository(controller)


    navigation(
        route = Rutas.INTERCAMBIOS,
        startDestination = VentanasIntercambios.SeleccionarCromoScreen.ruta
    ) {
        composable(route = VentanasIntercambios.SeleccionarCromoScreen.ruta) {
            val viewmodel = SeleccionarIdViewModel(intercambiosRepository,cromorepository, usuarioRepository)
            PantallaSeleccionarCromo(controller, viewmodel)
        }
    }

}
