package com.seunome.diariotreinos.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.seunome.diariotreinos.model.Treino

class TreinoViewModel : ViewModel() {
    var estaLogado by mutableStateOf(false)
        private set

    val treinos = mutableStateListOf(
        Treino(1, "Peito e Tríceps", "🏋️", "Treino de força para peito e tríceps.", 45),
        Treino(2, "Costas e Bíceps", "💪", "Treino de puxada para costas e bíceps.", 50),
        Treino(3, "Pernas", "🦵", "Treino completo para quadríceps, posteriores e glúteos.", 55),
        Treino(4, "Cardio", "🏃", "Sessão de corrida e condicionamento físico.", 30),
        Treino(5, "Abdômen", "🔥", "Treino rápido para fortalecer o core.", 20)
    )

    fun fazerLogin() {
        estaLogado = true
    }

    fun fazerLogout() {
        estaLogado = false
    }

    fun alternarConclusao(treinoId: Int) {
        val index = treinos.indexOfFirst { it.id == treinoId }
        if (index != -1) {
            treinos[index] = treinos[index].copy(concluidoHoje = !treinos[index].concluidoHoje)
        }
    }

    fun removerTodos() {
        treinos.clear()
    }

    fun buscarTreino(treinoId: Int): Treino? =
        treinos.find { it.id == treinoId }
}
