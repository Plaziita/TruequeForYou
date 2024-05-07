package com.example.intercromo.model

data class Usuario(
    val nombre: String?,
    val email: String?,
    val userId: String?,
    val imagenPerfil: String?,
    val direccion: String?,
    val valoracion: Double,
    val cromos: List<Cromo>?,
    val cromosFavoritos: List<String>?


){
   fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "userId" to this.userId.orEmpty(),
            "name" to this.nombre.orEmpty(),
            "email" to this.email.orEmpty(),
            "imagenPerfil" to this.imagenPerfil.orEmpty(),
            "direccion" to this.direccion.orEmpty(),
            "valoracion" to this.valoracion,
            "cromos" to this.cromos.orEmpty(),
            "cromosFavoritos" to this.cromosFavoritos.orEmpty()
        )
    }
}
