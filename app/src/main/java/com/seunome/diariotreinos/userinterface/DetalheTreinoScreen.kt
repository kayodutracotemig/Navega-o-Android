package com.seunome.diariotreinos.userinterface

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seunome.diariotreinos.model.Treino

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalheTreinoScreen(
    treino: Treino?,
    aoVoltar: () -> Unit,
    aoAlternarConclusao: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(treino?.nome ?: "Treino") },
                navigationIcon = {
                    IconButton(onClick = aoVoltar) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                }
            )
        }
    ) { padding ->
        if (treino == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Treino não encontrado.")
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(treino.emoji, fontSize = 72.sp)
            Spacer(Modifier.height(18.dp))
            Text(
                treino.nome,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(10.dp))
            Text(treino.descricao, style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(8.dp))
            Text("Duração: ${treino.duracaoMinutos} minutos")
            Spacer(Modifier.height(32.dp))
            Button(onClick = aoAlternarConclusao) {
                Text(
                    if (treino.concluidoHoje) {
                        "Desmarcar treino"
                    } else {
                        "Marcar como concluído"
                    }
                )
            }
        }
    }
}
