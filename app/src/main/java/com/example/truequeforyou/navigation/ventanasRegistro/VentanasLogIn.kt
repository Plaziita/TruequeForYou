package com.example.truequeforyou.navigation.ventanasRegistro

sealed class VentanasLogIn(
    val ruta : String
){
    object SplashScreen: VentanasLogIn(ruta = "splash_screen")
    object BienvenidosScreen: VentanasLogIn(ruta = "bienvenidos_screen")
    object GmailScreen: VentanasLogIn(ruta = "gmail_screen")
    object EmailScreen: VentanasLogIn(ruta = "email_screen")

}
