package com.example.intercromo.navigation.rutaInicio

sealed class VentanasInicio(
    val ruta : String
){
    object CromoScreen: VentanasInicio(ruta = "mostrar_cromo")
}
