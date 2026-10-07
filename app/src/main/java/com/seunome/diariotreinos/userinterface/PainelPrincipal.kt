package com.seunome.diariotreinos.userinterface

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.seunome.diariotreinos.model.Rota
import com.seunome.diariotreinos.viewmodel.TreinoViewModel

enum class AbaPrincipal {
    TREINOS,
    PROGRESSO,
    PERFIL
}

@Composable
fun PainelPrincipal(
    navController: NavHostController,
    viewModel: TreinoViewModel,
    abaAtual: AbaPrincipal
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = abaAtual == AbaPrincipal.TREINOS,
                    onClick = { navegarParaAba(navController, Rota.Treinos) },
                    icon = {
                        Icon(
                            Icons.Filled.FitnessCenter,
                            contentDescription = "Treinos"
                        )
                    },
                    label = { Text("Treinos") }
                )
                NavigationBarItem(
                    selected = abaAtual == AbaPrincipal.PROGRESSO,
                    onClick = { navegarParaAba(navController, Rota.Progresso) },
                    icon = {
                        Icon(
                            Icons.Filled.BarChart,
                            contentDescription = "Progresso"
                        )
                    },
                    label = { Text("Progresso") }
                )
                NavigationBarItem(
                    selected = abaAtual == AbaPrincipal.PERFIL,
                    onClick = { navegarParaAba(navController, Rota.Perfil) },
                    icon = {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = "Perfil"
                        )
                    },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { paddingValues ->
        when (abaAtual) {
            AbaPrincipal.TREINOS -> ListaTreinosScreen(
                modifier = Modifier.padding(paddingValues),
                viewModel = viewModel,
                aoAbrirDetalhe = { id ->
                    navController.navigate(Rota.DetalheTreino(id))
                }
            )

            AbaPrincipal.PROGRESSO -> ProgressoScreen(
                modifier = Modifier.padding(paddingValues),
                viewModel = viewModel
            )

            AbaPrincipal.PERFIL -> PerfilScreen(
                modifier = Modifier.padding(paddingValues),
                viewModel = viewModel,
                aoSair = {
                    viewModel.fazerLogout()
                    navController.navigate(Rota.Login) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}

private fun navegarParaAba(
    navController: NavHostController,
    destino: Rota
) {
    navController.navigate(destino) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
