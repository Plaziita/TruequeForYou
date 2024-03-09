package com.example.truequeforyou.navigation.ventanasRegistro

sealed class VentanasLogIn(
    val ruta : String
){
    object SplashScreen: VentanasLogIn(ruta = "splash_screen")
    object BienvenidosScreen: VentanasLogIn(ruta = "bienvenidos_screen")
    object LoginEmailScreen: VentanasLogIn(ruta = "loginEmail_screen")
    object EmailScreen: VentanasLogIn(ruta = "registerEmail_screen")

}
