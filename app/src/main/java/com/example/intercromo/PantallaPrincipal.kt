@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.intercromo

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.intercromo.navigation.barraNavegacion.Barra
import com.example.intercromo.navigation.barraNavegacion.BotonesDeNavegar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaMain(controller: NavHostController){
    val navController = rememberNavController()
    /* gestiona el estado de navegacion entre ellas*/

    /*Estructura la pantalla para poder añadilre una barra superior o inferior*/
    Scaffold(
        bottomBar = { Barra(navController) }
    ) {
        BotonesDeNavegar(navController,controller)
    }

}





