package com.example.intercromo.navigation.manejadorRutas

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.intercromo.barraNavegacion
import com.example.intercromo.navigation.rutaInicio.NavegacionInicio
import com.example.intercromo.navigation.rutaPerfil.NavegacionPerfil
import com.example.intercromo.navigation.ventanasRegistro.NavegacionLogin


@Composable
fun ManejadorRutas(navController: NavHostController) {
    val controllerBarraNavegacion = rememberNavController()

    NavHost(
        navController = navController,
        route = Rutas.MANEJADOR,
        startDestination = Rutas.REGISTRO
    ){
        NavegacionPerfil(navController)
        NavegacionLogin(navController = navController)
        NavegacionInicio(navController)
        composable(route = Rutas.BARRANAVEGACION) {
            barraNavegacion(navController, controllerBarraNavegacion)
        }
    }
}
object Rutas {
    const val MANEJADOR = "manejador_ruta"
    const val REGISTRO = "registro_ruta"
    const val BARRANAVEGACION = "navegacion_ruta"
    const val PERFIL = "perfil_ruta"
    const val INICIO = "inicio_ruta"
}