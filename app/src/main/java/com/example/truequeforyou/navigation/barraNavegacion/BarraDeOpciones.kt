package com.example.truequeforyou.navigation.barraNavegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
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
    object Tradear : BarraDeOpciones(
        ruta = "tradear",
        titulo = "Tradear",
        icono = Icons.Default.ShoppingCart
    )
    object Mensajes : BarraDeOpciones(
        ruta = "mensajes",
        titulo = "Mensajes",
        icono = Icons.Default.MailOutline
    )
    object Perfil : BarraDeOpciones(
        ruta = "perfil",
        titulo = "Perfil",
        icono = Icons.Default.Person
    )

}
