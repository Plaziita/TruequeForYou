package com.example.intercromo.model

data class Usuario(
    val nombre: String?,
    val email: String?,
    val userId: String?,
    val imagenPerfil: String?,
    val valoracion: Double,
    val cromosFavoritos: List<String>?,
    val peticiones: List<String>?
){
   fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "userId" to this.userId.orEmpty(),
            "name" to this.nombre.orEmpty(),
            "email" to this.email.orEmpty(),
            "imagenPerfil" to this.imagenPerfil.orEmpty(),
            "valoracion" to this.valoracion,
            "cromosFavoritos" to this.cromosFavoritos.orEmpty(),
            "peticiones" to this.peticiones.orEmpty()
        )
    }
}
