package com.example.agrocompnativ.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.agrocompnativ.constantes.Constantes
import com.example.agrocompnativ.models.SetoresViewModel
import com.example.agrocompnativ.ui.theme.BackgroudColorBlueBlack
import com.example.agrocompnativ.ui.theme.BackgroudColorBlueLight
import com.example.agrocompnativ.ui.theme.BackgroudColorBlueMedium
import com.example.agrocompnativ.ui.theme.TitleWindowBlueBlack


@Composable
fun modo(nome : String, index : Int, selecionado : Int, onClick: () -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 30.dp)
            .clickable(onClick = onClick),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = if (index == selecionado) BackgroudColorBlueLight else BackgroudColorBlueMedium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = nome, style = MaterialTheme.typography.bodyMedium, color = Color.White, modifier = Modifier.padding(start = 10.dp))
        }

    }

}

@Composable
fun SelecionarModo(viewModel: SetoresViewModel){
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroudColorBlueMedium),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(color = TitleWindowBlueBlack)
                ){
                    Text(text = "Selecionar Modo", style = MaterialTheme.typography.bodyLarge, color = Color.White, modifier = Modifier.padding(start = 10.dp))
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                        .background(color = BackgroudColorBlueMedium)
                        .verticalScroll(rememberScrollState()),
                ) {
                    modo("Manual", 0, viewModel.irrigacaoStatus.status){
                        viewModel.setIrriStatus(Constantes.MODO_MANUAL)
                    }
                    modo("Automático", 1,viewModel.irrigacaoStatus.status){
                        viewModel.setIrriStatus(Constantes.MODO_AUTOMATICO)
                    }
                    modo("Desligado", 2, viewModel.irrigacaoStatus.status){
                        viewModel.setIrriStatus(Constantes.MODO_DESLIGADO)
                    }
                }
            }

    }
}


@Composable
fun ModoView(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    navController: NavController,
    viewModel: SetoresViewModel
){
    Box(
        modifier = Modifier.padding(paddingValues)
    ){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroudColorBlueBlack)
            .padding(20.dp),
            contentAlignment = Alignment.Center,

            ){
            SelecionarModo(viewModel)
        }
    }

}


