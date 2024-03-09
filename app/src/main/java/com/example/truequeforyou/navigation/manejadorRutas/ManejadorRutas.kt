package com.example.truequeforyou.navigation.manejadorRutas

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.truequeforyou.PantallaMain
import com.example.truequeforyou.navigation.ventanasRegistro.NavegacionLogin


@Composable
fun ManejadorRutas(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Rutas.MANEJADOR,
        startDestination = Rutas.REGISTRO
    ){
        NavegacionLogin(navController = navController)
        composable(route = Rutas.BARRANAVEGACION) {
            PantallaMain()
        }
    }
}
object Rutas {
    const val MANEJADOR = "manejador_ruta"
    const val REGISTRO = "registro_ruta"
    const val BARRANAVEGACION = "navegacion_ruta"
}