package com.example.exa_recu_ricardogr.ui

data class GameUiState(
    val nombreAsignatura: String = "",
    val horasAsignatura: String = "0",
    val ultimaAccion: String = "No has hecho ninguna acción",
    val resumen: ArrayList<AsignaturasRegistradas> = arrayListOf(),
    val totalHoras: Int = 0,
    val totalPrecio: Int = 0
)
