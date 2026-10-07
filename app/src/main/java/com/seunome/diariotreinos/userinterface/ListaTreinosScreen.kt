package com.seunome.diariotreinos.userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seunome.diariotreinos.model.Treino
import com.seunome.diariotreinos.viewmodel.TreinoViewModel

@Composable
fun ListaTreinosScreen(
    modifier: Modifier = Modifier,
    viewModel: TreinoViewModel,
    aoAbrirDetalhe: (Int) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            "Seus treinos",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(20.dp)
        )

        if (viewModel.treinos.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Nenhum treino cadastrado ainda.",
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(viewModel.treinos, key = { it.id }) { treino ->
                    CardTreino(
                        treino = treino,
                        aoClicar = { aoAbrirDetalhe(treino.id) },
                        aoAlternar = { viewModel.alternarConclusao(treino.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun CardTreino(
    treino: Treino,
    aoClicar: () -> Unit,
    aoAlternar: () -> Unit
) {
    Card(
        onClick = aoClicar,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(treino.emoji, fontSize = 22.sp)
            }
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(treino.nome, fontWeight = FontWeight.SemiBold)
                Text(
                    "${treino.duracaoMinutos} min · ${treino.descricao}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            IconButton(onClick = aoAlternar) {
                Icon(
                    imageVector = if (treino.concluidoHoje) {
                        Icons.Filled.CheckCircle
                    } else {
                        Icons.Filled.RadioButtonUnchecked
                    },
                    contentDescription = if (treino.concluidoHoje) {
                        "Concluído hoje, tocar para desmarcar"
                    } else {
                        "Marcar treino como concluído"
                    },
                    tint = if (treino.concluidoHoje) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.secondary
                    }
                )
            }
        }
    }
}
