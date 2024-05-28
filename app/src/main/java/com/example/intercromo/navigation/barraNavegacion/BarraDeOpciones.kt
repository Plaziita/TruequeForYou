package com.example.intercromo.navigation.barraNavegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
/* Clase sellada en donde nos creamos un objeto de cada pantalla por la que vamos a navegar*/
sealed class BarraDeOpciones(
    val ruta : String,
    val titulo: String,
    val icono: ImageVector
){
    object Inicio : BarraDeOpciones(
        ruta = "inicio",
        titulo = "Inicio",
        icono = Icons.Default.Home
    )
    object Favoritos : BarraDeOpciones(
        ruta = "favoritos",
        titulo = "Favoritos",
        icono = Icons.Default.FavoriteBorder
    )
    object Upload : BarraDeOpciones(
        ruta = "upload",
        titulo = "Subir",
        icono = Icons.Default.ArrowUpward
    )
    object Intercambios : BarraDeOpciones(
        ruta = "mensajes",
        titulo = "Peticiones",
        icono = Icons.Default.Mail
    )
    object Perfil : BarraDeOpciones(
        ruta = "perfil",
        titulo = "Perfil",
        icono = Icons.Default.Person
    )

}
