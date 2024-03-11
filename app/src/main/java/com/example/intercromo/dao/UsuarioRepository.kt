package com.example.intercromo.dao

import com.example.intercromo.db.FireBase

class UsuarioRepository {
    val baseDatos = FireBase()

    val usuarios = baseDatos.db.collection("Cromos");



}