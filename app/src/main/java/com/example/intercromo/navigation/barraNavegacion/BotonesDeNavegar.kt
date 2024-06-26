@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.intercromo

import IntercambiosViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
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
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.dao.IntercambiosRepository
import com.example.intercromo.dao.UsuarioRepository
import com.example.intercromo.navigation.barraNavegacion.BarraDeOpciones
import com.example.intercromo.presentation.favoritos.FavoritosViewModel
import com.example.intercromo.presentation.favoritos.PantallaFavoritos
import com.example.intercromo.presentation.inicio.InicioViewModel
import com.example.intercromo.presentation.inicio.PantallaInicio
import com.example.intercromo.presentation.intercambios.PantallaIntercambios
import com.example.intercromo.presentation.perfil.PantallaPerfil
import com.example.intercromo.presentation.uploadcromo.UploadCromoScreen
import com.example.intercromo.presentation.uploadcromo.UploadCromoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun barraNavegacion(controller: NavHostController, controllerBarraNavegacion: NavHostController){

    /* gestiona el estado de navegacion entre ellas*/

    /*Estructura la pantalla para poder añadilre una barra superior o inferior*/
    Scaffold(
        bottomBar = { Barra(controllerBarraNavegacion) }
    ) {
        BotonesDeNavegar(controller, controllerBarraNavegacion)
    }



}

@Composable
fun Barra (navController: NavHostController){
    val screens = listOf(
        BarraDeOpciones.Inicio,
        BarraDeOpciones.Favoritos,
        BarraDeOpciones.Upload,
        BarraDeOpciones.Intercambios,
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

@SuppressLint("SuspiciousIndentation")
@Composable
fun BotonesDeNavegar(navController: NavHostController, controllerBarraNavegacion: NavHostController){

    //El nuevo navController2 es el de VentanasLogin
    val cromorepository = CromoRepository()
    val intercambiosRepository = IntercambiosRepository(navController)
    val usuarioRepository = UsuarioRepository(navController)

    NavHost(
        navController = controllerBarraNavegacion,
        startDestination = BarraDeOpciones.Inicio.ruta
    ){
        composable(route = BarraDeOpciones.Inicio.ruta) {
            val viewmodelinicio = InicioViewModel(cromorepository)
            PantallaInicio(viewmodelinicio, navController)
        }
        composable(route = BarraDeOpciones.Favoritos.ruta) {
            val viewmodelFavorito = FavoritosViewModel(cromorepository)
            PantallaFavoritos(viewmodelFavorito, navController)
        }
        composable(route = BarraDeOpciones.Upload.ruta) {
            val viewmodelUpload = UploadCromoViewModel(cromorepository)
            UploadCromoScreen(viewmodelUpload)
        }
        composable(route = BarraDeOpciones.Intercambios.ruta) {
            val viewmodelIntercambios = IntercambiosViewModel(intercambiosRepository,cromorepository, usuarioRepository)
            PantallaIntercambios(viewmodelIntercambios)
        }
        composable(route = BarraDeOpciones.Perfil.ruta) {
            PantallaPerfil(navController)
        }
    }
}
