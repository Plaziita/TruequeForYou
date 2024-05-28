package com.example.intercromo.model

import java.sql.Timestamp


data class Intercambios(
    val timestamp: Timestamp,
    val idUserEmisor: String,
    val idUserRemitente: String,
    val idCromoEmisor: String,
    val idCromoRemitente: String,
    val estado: String,
){
    fun toMap(): MutableMap<Any, Any> {
        return mutableMapOf(
            "timestamp" to this.timestamp,
            "idUserEmisor" to this.idUserEmisor,
            "idUserRemitente" to this.idUserRemitente,
            "idCromoEmisor" to this.idCromoEmisor,
            "idCromoRemitente" to this.idCromoRemitente,
            "estado" to "pendiente"
        )
    }
}
