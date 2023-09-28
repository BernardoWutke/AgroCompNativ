package com.example.agrocompnativ.views

import android.app.TimePickerDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.agrocompnativ.R
import com.example.agrocompnativ.models.SetoresViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean


@Composable
fun bombaConfig(
    context: android.content.Context = LocalContext.current,
    timeBomba: String,
    viewModel: SetoresViewModel,
) {

    var selecTimeText by remember{
        mutableStateOf(timeBomba)
    }

    val timepicker = TimePickerDialog(context,
        { _, hour : Int, minute: Int ->
            viewModel.setBombaTime(hour, minute)
            selecTimeText = viewModel.getBombaTimeFormated()

        }, 0, 0, true)


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { timepicker.show() }


    ){
        Card{
            Column(
                Modifier
                    .fillMaxWidth()

            ) {
                Box(
                    Modifier
                        .background(color = Color(0xFF00384D))
                        .fillMaxWidth()

                ){
                    Text(
                        text = "Horario de Ligar",
                        modifier = Modifier.padding(start = 5.dp),
                        color = Color.White,
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
                Column (
                    Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFF338AAA))
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center


                ) {
                    Row (
                        Modifier
                            .fillMaxWidth(),

                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            //icone de relógio
                            painter = painterResource(id = R.drawable.baseline_timer_24),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )


                        Text(text = "Hora ",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Card(
                            colors = CardDefaults
                                .cardColors(Color(0xFF00384D)),
                            shape = RoundedCornerShape(10.dp),

                            ) {
                            Text(
                                //select time format 00:00
                                text = selecTimeText,
                                Modifier.padding(5.dp),
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge,
                                )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun setorConfig(
    viewModel: SetoresViewModel,
    context: Context,
    timeSetor: String,
    scope: CoroutineScope,
    index : Int = 0,
) {


    var selecTimeText by remember{
        mutableStateOf(timeSetor)
    }

    var hour_buff : Int = 0;
    var minute_buff : Int = 0;

    val timepicker = TimePickerDialog(context,
        { _, hour : Int, minute: Int ->

            //uma variavel para guarda se foi salvo corretamente no banco de dados, que so recebe true se o banco de dados for atualizado
            var salvo = AtomicBoolean()
            hour_buff = hour
            minute_buff = minute

            scope.launch(Dispatchers.IO){
                salvo.set(viewModel.modificarTimeSetor(hour_buff, minute_buff, index))
                selecTimeText = viewModel.setores[index].time

            }
            //se não foi salvo, mostra um toast
            scope.launch(Dispatchers.Main){
                if(!salvo.get()){
                    Toast.makeText(context, "Erro ao salvar", Toast.LENGTH_SHORT).show()
                }
            }




        }, 0, 0, true)


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                timepicker.show()

            }


    ){
        Card{
            Column(
                Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    Modifier
                        .background(color = Color(0xFF338AAA))
                        .fillMaxWidth()

                ){
                    Text(
                        text = "Setor ${index}",
                        modifier = Modifier.padding(start = 5.dp),
                        color = Color.White,
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
                Column (
                    Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFF6FA5B9))
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center


                ) {
                    Row (
                        Modifier
                            .fillMaxWidth(),

                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            //icone de relógio
                            painter = painterResource(id = R.drawable.baseline_timer_24),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )



                        Text(text = "Tempo ",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Card(
                            colors = CardDefaults
                                .cardColors(Color(0xFF338AAA)),
                            shape = RoundedCornerShape(10.dp),

                            ) {
                            Text(
                                //select time format 00:00
                                text = selecTimeText,
                                Modifier.padding(5.dp),
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge,


                                )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun viewConfiguracoes(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    navController: NavController,
    viewModel: SetoresViewModel ,
){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    Box(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())  //scroll
        ) {
            bombaConfig(timeBomba = viewModel.getBombaTimeFormated(), viewModel = viewModel)
            for(i in viewModel.setores.indices){
                setorConfig(timeSetor = viewModel.setores[i].time, index = i, viewModel = viewModel, context = context,scope = scope)
            }
        }
    }
}
