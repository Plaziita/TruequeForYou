package com.example.intercromo.navigation.rutaIntercambios

sealed class VentanasIntercambios(
    val ruta : String
) {
    object SeleccionarCromoScreen: VentanasIntercambios(ruta = "seleccionar_cromo/{idEmisor}/{idRemitente}/{idCromoRemitente}")

}
