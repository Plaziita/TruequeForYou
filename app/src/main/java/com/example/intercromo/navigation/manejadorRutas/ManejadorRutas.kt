package com.example.intercromo.navigation.manejadorRutas

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.intercromo.PantallaMain
import com.example.intercromo.navigation.rutaPerfil.NavegacionPerfil
import com.example.intercromo.navigation.ventanasRegistro.NavegacionLogin


@Composable
fun ManejadorRutas(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Rutas.MANEJADOR,
        startDestination = Rutas.REGISTRO
    ){
        NavegacionPerfil(navController = navController)
        NavegacionLogin(navController = navController)
        composable(route = Rutas.BARRANAVEGACION) {
            PantallaMain(navController)
        }
    }
}
object Rutas {
    const val MANEJADOR = "manejador_ruta"
    const val REGISTRO = "registro_ruta"
    const val BARRANAVEGACION = "navegacion_ruta"
    const val PERFIL = "perfil_ruta"
}