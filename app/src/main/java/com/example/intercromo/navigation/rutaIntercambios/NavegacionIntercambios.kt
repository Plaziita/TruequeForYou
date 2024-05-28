package com.example.intercromo.navigation.rutaIntercambios

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.dao.IntercambiosRepository
import com.example.intercromo.navigation.manejadorRutas.Rutas
import com.example.intercromo.presentation.intercambios.IntercambiosViewModel
import com.example.intercromo.presentation.intercambios.PantallaSeleccionarCromo

fun NavGraphBuilder.NavegacionIntercambios(controller: NavHostController){

    val repository = IntercambiosRepository()
    val cromorepository = CromoRepository()


    navigation(
        route = Rutas.INTERCAMBIOS,
        startDestination = VentanasIntercambios.SeleccionarCromoScreen.ruta
    ) {
        composable(route = VentanasIntercambios.SeleccionarCromoScreen.ruta) {
            val viewmodel = IntercambiosViewModel(repository,cromorepository)
            PantallaSeleccionarCromo(controller, viewmodel)
        }
    }

}
