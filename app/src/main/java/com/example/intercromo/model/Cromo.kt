package com.example.intercromo.model

data class Cromo(
    val nombre: String,
    val descripcion: String,
    val imagen: String?,
    val categoria: String,
    val idUsuario: String,
    val favorito: Boolean,
    val cromoId: String?
){
    fun toMap(): MutableMap<Any, Any> {
        return mutableMapOf(
            "nombre" to this.nombre,
            "descripcion" to this.descripcion,
            "imagen" to this.imagen.orEmpty(),
            "categoria" to this.categoria,
            "id_usuario" to this.idUsuario,
            "cromoId" to this.cromoId.orEmpty(),
            "favorito" to false
        )
    }
}
