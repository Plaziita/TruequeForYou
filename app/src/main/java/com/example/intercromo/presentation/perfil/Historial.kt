package com.example.intercromo.presentation.perfil

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.intercromo.navigation.rutaPerfil.VentanasPerfil

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaHistorial(navController: NavController){

    Scaffold(
        topBar = {
            BarraSuperiorHistorial(navController)
        }
    ) {
        Historial()
    }
}

@Composable
fun Historial() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Text(
            text = "Historial"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorHistorial(navController: NavController) {
    TopAppBar(
        title = {
            Row {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Navigation Icon",
                    tint = Color.Black,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(VentanasPerfil.PerfilScreen.ruta)
                        }
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "Historial"
                )
            }
        }
    )
}

@Composable
@Preview
fun PantallaHistorialPreview(){
    PantallaHistorial(navController = rememberNavController())
}

