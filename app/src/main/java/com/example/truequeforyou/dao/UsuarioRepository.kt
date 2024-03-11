package com.example.truequeforyou.dao

import com.example.truequeforyou.db.FireBase

class UsuarioRepository {
    val baseDatos = FireBase()

    val usuarios = baseDatos.db.collection("Cromos");



}