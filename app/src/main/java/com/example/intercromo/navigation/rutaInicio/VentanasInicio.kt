package com.example.intercromo.navigation.rutaInicio

sealed class VentanasInicio(
    val ruta : String
){
    object CromoScreen: VentanasInicio(ruta = "mostrar_cromo/{cromo}")

    object InicioScreen: VentanasInicio(ruta = "inicio_screen")

    object CromoFiltradoScreen: VentanasInicio(ruta = "cromos_filtrados/{query}")


}
