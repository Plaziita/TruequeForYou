@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.truequeforyou

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.truequeforyou.bottomBar.Barra
import com.example.truequeforyou.bottomBar.BotonesDeNavegar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaMain(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { Barra(navController = navController)}
    ) {
        BotonesDeNavegar(navController = navController)
    }
}

fun mariete(){
    val mensaje = "tontotto"
    val tonto = "tontito"
}



