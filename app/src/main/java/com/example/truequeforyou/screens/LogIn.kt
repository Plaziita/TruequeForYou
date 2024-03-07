package com.example.truequeforyou.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun InicioSesion(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ){
        Column {
            Text(
                text = "Usuario",
                color = Color.White,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Contraseña",
                color = Color.White,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@Composable
@Preview
fun InicioSesionPreview(){
    InicioSesion()
}