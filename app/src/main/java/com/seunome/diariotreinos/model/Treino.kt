package com.seunome.diariotreinos.model

data class Treino(
    val id: Int,
    val nome: String,
    val emoji: String,
    val descricao: String,
    val duracaoMinutos: Int,
    val concluidoHoje: Boolean = false
)
