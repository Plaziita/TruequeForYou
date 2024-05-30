package com.example.intercromo.model


data class Intercambios(
    val idUserEmisor: String,
    val idUserRemitente: String,
    val idCromoEmisor: String,
    val idCromoRemitente: String,
    val estado: String,
    val idIntercambio:String
){
    fun toMap(): MutableMap<Any, Any> {
        return mutableMapOf(
            "idUserEmisor" to this.idUserEmisor,
            "idUserRemitente" to this.idUserRemitente,
            "idCromoEmisor" to this.idCromoEmisor,
            "idCromoRemitente" to this.idCromoRemitente,
            "estado" to "pendiente",
            "idIntercambio" to this.idIntercambio
        )
    }
}
