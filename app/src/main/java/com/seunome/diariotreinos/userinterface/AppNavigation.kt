package com.seunome.diariotreinos.userinterface

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.seunome.diariotreinos.model.Rota
import com.seunome.diariotreinos.viewmodel.TreinoViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: TreinoViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Rota.Splash
    ) {
        composable<Rota.Splash> {
            SplashScreen(
                estaLogado = viewModel.estaLogado,
                aoTerminarVerificacao = { logado ->
                    val destino = if (logado) Rota.Treinos else Rota.Login
                    navController.navigate(destino) {
                        popUpTo(Rota.Splash) { inclusive = true }
                    }
                }
            )
        }

        composable<Rota.Login> {
            LoginScreen(
                aoLogar = {
                    viewModel.fazerLogin()
                    navController.navigate(Rota.Treinos) {
                        popUpTo(Rota.Login) { inclusive = true }
                    }
                }
            )
        }

        composable<Rota.Treinos> {
            PainelPrincipal(
                navController = navController,
                viewModel = viewModel,
                abaAtual = AbaPrincipal.TREINOS
            )
        }

        composable<Rota.Progresso> {
            PainelPrincipal(
                navController = navController,
                viewModel = viewModel,
                abaAtual = AbaPrincipal.PROGRESSO
            )
        }

        composable<Rota.Perfil> {
            PainelPrincipal(
                navController = navController,
                viewModel = viewModel,
                abaAtual = AbaPrincipal.PERFIL
            )
        }

        composable<Rota.DetalheTreino> { backStackEntry ->
            val rota: Rota.DetalheTreino = backStackEntry.toRoute()
            DetalheTreinoScreen(
                treino = viewModel.buscarTreino(rota.treinoId),
                aoVoltar = { navController.popBackStack() },
                aoAlternarConclusao = {
                    viewModel.alternarConclusao(rota.treinoId)
                }
            )
        }
    }
}
