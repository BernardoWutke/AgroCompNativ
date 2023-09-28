package com.example.agrocompnativ.views.Home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.agrocompnativ.models.Setor
import com.example.agrocompnativ.models.SetoresViewModel
import com.example.agrocompnativ.views.Home.componentes.irrigacaoStatus
import com.example.agrocompnativ.views.Home.componentes.setorStatus



val modos = listOf("Manual", "Automático", "Desligado");

@Composable
fun homeView(
    paddingValues: PaddingValues = PaddingValues(10.dp),
    navController: NavController,
    viewModel: SetoresViewModel
){
    Box(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(10.dp)
        ) {
            irrigacaoStatus(viewModel.irrigacaoStatus.horaFim, viewModel.irrigacaoStatus.horaInicio, modos[viewModel.irrigacaoStatus.status])
            for (i in viewModel.setores.indices) {
                var setor = viewModel.setores[i];
                Box(modifier = Modifier.padding(top = 20.dp))
                setorStatus(setor,viewModel)
            }
        }
    }
}

