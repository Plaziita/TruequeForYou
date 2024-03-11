@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.truequeforyou

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.truequeforyou.navigation.barraNavegacion.Barra
import com.example.truequeforyou.navigation.barraNavegacion.BotonesDeNavegar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaMain(){
    /* gestiona el estado de navegacion entre ellas*/
    val navController = rememberNavController()

    /*Estructura la pantalla para poder añadilre una barra superior o inferior*/
    Scaffold(
        bottomBar = { Barra(navController = navController) }
    ) {
        BotonesDeNavegar(navController = navController)
    }

}





