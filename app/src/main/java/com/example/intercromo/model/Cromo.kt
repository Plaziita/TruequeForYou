package com.example.intercromo.model

data class Cromo(
    val nombre: String,
    val descripcion: String,
    val imagen: String?,
    val categoria: String,
    val id_usuario: String,
    val favorito: Boolean,
    val cromoId: String?
){
    fun toMap(): MutableMap<Any, Any> {
        return mutableMapOf(
            "nombre" to this.nombre,
            "descripcion" to this.descripcion,
            "imagenl" to this.imagen.orEmpty(),
            "categoria" to this.categoria,
            "id_usuario" to this.id_usuario,
            "cromoId" to this.cromoId.orEmpty(),
            "favorito" to false
        )
    }
}
