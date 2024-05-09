package com.example.intercromo.navigation.rutaInicio

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.intercromo.dao.ChatRepository
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.navigation.manejadorRutas.Rutas
import com.example.intercromo.presentation.CromoScreen.CromoScreenViewModel
import com.example.intercromo.presentation.CromoScreen.PantallaCromo
import com.example.intercromo.presentation.inicio.InicioViewModel
import com.example.intercromo.presentation.inicio.PantallaInicio
import com.example.intercromo.presentation.inicio.filtrar.FiltradoViewModel
import com.example.intercromo.presentation.inicio.filtrar.PantallaFiltrada

fun NavGraphBuilder.NavegacionInicio(controllerOpciones: NavHostController){

    val cromorepository = CromoRepository()
    val chatRepository = ChatRepository()

    navigation(
        route = Rutas.INICIO,
        startDestination = VentanasInicio.InicioScreen.ruta
    ){
        composable(route = VentanasInicio.CromoScreen.ruta) {
            val viewmodelPantallaCromo = CromoScreenViewModel(cromorepository,chatRepository)
            PantallaCromo(controllerOpciones, viewmodelPantallaCromo)
        }
        composable(route = VentanasInicio.InicioScreen.ruta) {
            val viewmodelInicio = InicioViewModel(cromorepository)
            PantallaInicio(viewmodelInicio, controllerOpciones)
        }
        composable(route = VentanasInicio.CromoFiltradoScreen.ruta) {
            val viewmodelInicio = InicioViewModel(cromorepository)
            val viewmodelFiltrado = FiltradoViewModel(cromorepository)
            PantallaFiltrada(viewmodelFiltrado,controllerOpciones)
        }
    }
}