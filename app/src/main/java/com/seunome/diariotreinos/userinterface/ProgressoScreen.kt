package com.seunome.diariotreinos.userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.seunome.diariotreinos.viewmodel.TreinoViewModel

@Composable
fun ProgressoScreen(
    modifier: Modifier = Modifier,
    viewModel: TreinoViewModel
) {
    val total = viewModel.treinos.size
    val concluidos = viewModel.treinos.count { it.concluidoHoje }
    val percentual = if (total == 0) 0f else concluidos.toFloat() / total

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Progresso de hoje",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(24.dp))
        Text("$concluidos de $total treinos concluídos")
        Spacer(Modifier.height(16.dp))
        LinearProgressIndicator(
            progress = { percentual },
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}
