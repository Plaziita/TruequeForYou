package com.example.intercromo.navigation.barraNavegacion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.wear.compose.material.ContentAlpha
import com.example.intercromo.presentation.PantallaFavoritos
import com.example.intercromo.presentation.PantallaInicio
import com.example.intercromo.presentation.PantallaMensajes
import com.example.intercromo.presentation.UploadCromoScreen
import com.example.intercromo.presentation.perfil.PantallaPerfil
/* Esta función gestiona como vamos  a navegar entre las pantallas de nuestra sealed class */
@Composable

fun BotonesDeNavegar(navController: NavHostController, navController2: NavHostController){

    //El nuevo navController2 es el de VentanasLogin

        NavHost(
            navController = navController,
            startDestination = BarraDeOpciones.Inicio.ruta
        ){
            composable(route = BarraDeOpciones.Inicio.ruta) {
                PantallaInicio()
            }
            composable(route = BarraDeOpciones.Favoritos.ruta) {
                PantallaFavoritos()
            }
            composable(route = BarraDeOpciones.Upload.ruta) {
                UploadCromoScreen()
            }
            composable(route = BarraDeOpciones.Mensajes.ruta) {
                PantallaMensajes()
            }
            composable(route = BarraDeOpciones.Perfil.ruta) {
                PantallaPerfil(navController2)
            }
        }
}

/* funcion para cada vez q pulses a un elemento de la barra de abajo te lleve a su pantalla*/
@Composable
fun Barra (navController: NavHostController){
    val screens = listOf(
        BarraDeOpciones.Inicio,
        BarraDeOpciones.Favoritos,
        BarraDeOpciones.Upload,
        BarraDeOpciones.Mensajes,
        BarraDeOpciones.Perfil
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation{
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: BarraDeOpciones,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(
                text = screen.titulo,
                fontSize = 10.sp,
                color = Color.Black
            )
        },
        icon = {
            Icon(
                imageVector = screen.icono,
                contentDescription = "Navigation Icon",
                tint = Color.Black
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.ruta
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.ruta) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        modifier = Modifier
            .background(Color(0xFFFFA500))
    )
}