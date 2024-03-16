package com.example.intercromo.navigation.rutaPerfil

sealed class VentanasPerfil(
    val ruta : String
    ){
        object PerfilScreen: VentanasPerfil(ruta = "perfil_screen")
        object AdquisicionesScreen: VentanasPerfil(ruta = "adquisiciones_screen")
        object TransferenciasScreen: VentanasPerfil(ruta = "transferencias_screen")
        object HistorialScreen: VentanasPerfil(ruta = "historial_screen")

}
