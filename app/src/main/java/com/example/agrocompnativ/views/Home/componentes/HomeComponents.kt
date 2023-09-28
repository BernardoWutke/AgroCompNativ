package com.example.agrocompnativ.views.Home.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrocompnativ.models.Setor
import com.example.agrocompnativ.models.SetoresViewModel
import com.example.agrocompnativ.ui.theme.BackgroudColorBlueLight
import com.example.agrocompnativ.ui.theme.BackgroudColorBlueMedium
import com.example.agrocompnativ.ui.theme.BackgroudColorButtonOff
import com.example.agrocompnativ.ui.theme.BackgroudColorButtonOn

@Composable
fun irrigacaoStatus(horaFim: String, horaInicio: String, status: String) {
    Card(

    ) {
        Box(
            Modifier.background(color = BackgroudColorBlueMedium)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                verticalArrangement = Arrangement.Center


            ) {
                Text(text = "Irrigação ", style = MaterialTheme.typography.bodyLarge, color = Color.White, modifier = Modifier.padding(start = 10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()

                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp, horizontal = 10.dp),

                        ){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),

                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically

                        ) {

                            Text( text = "Hora de inicio", style = MaterialTheme.typography.bodyMedium, color = Color.White)
                            Text(text = horaInicio, style = MaterialTheme.typography.bodyLarge, color = Color.White)
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text( text = "Hora do fim", style = MaterialTheme.typography.bodyMedium, color = Color.White)
                            Text(text = horaFim, style = MaterialTheme.typography.bodyLarge, color = Color.White)
                        }
                    }
                }
                //se a bomba esta ligada ou desligada
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp, horizontal = 10.dp),

                    ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),

                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text( text = "Estado ", style = MaterialTheme.typography.bodyMedium, color = Color.White,
                            modifier = Modifier.padding(end = 10.dp)

                        )
                        Text(text = status, style = MaterialTheme.typography.bodyLarge, color = Color.White,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                    }
                }
            }

        }
    }
}









@Composable
private fun modoHorarios(setor: Setor){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp),

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Hora de inicio",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            Text(
                text = setor.horaLigar,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Hora do fim",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            Text(
                text = setor.horaDesligar,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}

@Composable
private fun modoDesligado(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp),

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Esse setor não irá ser ligado",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}



@Composable
private fun modoManual(setor: Setor, viewModel: SetoresViewModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp),

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                Modifier
                    .clickable {
                        viewModel.alterarModoManualSetor(setor.index,true)
                    }
            ) {
                Box(
                    Modifier
                        //transparente
                        .background(color = if (setor.status) BackgroudColorButtonOn else BackgroudColorBlueLight)
                        .padding(horizontal = 20.dp, vertical = 10.dp)

                ){
                    Text(
                        text = "Ligar",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }
            }

            Card(
                Modifier
                    .clickable {
                        viewModel.alterarModoManualSetor(setor.index,false)
                    }
            ) {
                Box(
                    Modifier
                        //transparente
                        .background(color = if (setor.status) BackgroudColorBlueLight else BackgroudColorButtonOff)
                        .padding(horizontal = 20.dp, vertical = 10.dp)

                ){
                    Text(
                        text = "Desligar",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun setorStatus(setor: Setor, viewModel: SetoresViewModel) {
    Card(
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Row(
                modifier = Modifier
                    .background(color = Color(0xFF338AAA))
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            )
            {
                Text(text = "Setor " + setor.index, style = MaterialTheme.typography.bodyLarge, color = Color.White, modifier = Modifier.padding(start = 10.dp))
                Text(
                    text = if (setor.status) "Ligado" else "Desligado",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
            Box(
                modifier = Modifier
                    .background(color = BackgroudColorBlueLight)
                    .fillMaxWidth()

            ) {
                if(viewModel.irrigacaoStatus.status == 0){
                    modoManual(setor = setor, viewModel = viewModel)
                } else if (setor.horaLigar != "00:00" || setor.horaDesligar != "00:00" ) {
                    modoHorarios(setor = setor)
                } else  {
                    modoDesligado()
                }
            }
        }
    }
}


