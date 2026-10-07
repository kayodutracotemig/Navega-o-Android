package com.seunome.diariotreinos.model

import kotlinx.serialization.Serializable

sealed interface Rota {
    @Serializable
    data object Splash : Rota

    @Serializable
    data object Login : Rota

    @Serializable
    data object Treinos : Rota

    @Serializable
    data object Progresso : Rota

    @Serializable
    data object Perfil : Rota

    @Serializable
    data class DetalheTreino(val treinoId: Int) : Rota
}
