package com.example.truequeforyou.model

data class Usuario(
    val nombre: String,
    val email: String,
    val apellido: String,
    val imagenPerfil: String,
    val direccion: String,
    val nickname: String,
    val valoracion: Double,
    val cromo: List<Cromo>

)
