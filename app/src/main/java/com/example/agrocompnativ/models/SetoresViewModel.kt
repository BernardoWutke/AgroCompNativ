package com.example.agrocompnativ.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.agrocompnativ.constantes.Constantes
import java.sql.Time

data class Setor(
    var time: String = "00:00",
    val index: Int,
    var horaLigar: String,
    var horaDesligar: String,
    var status: Boolean,
    var manualValue: Boolean = false
)

data class irrigacao(
    var horaInicio: String,
    var horaFim: String,
    var status: Int,
    var bombaTime: Time
)





class SetoresViewModel(): ViewModel() {
    private val dataSource: DataSource = DataSource();

    //uma lista de setores, quando alguma coisa mudar em algum setor dentro dela, o compose vai recompor a tela
    private var _setores: SnapshotStateList<Setor>  = mutableStateListOf<Setor>().apply {
        addAll(getSetoresList())
    }


    private var _irrigacaoStatus = mutableStateOf(irrigacao("00:00", "00:00", Constantes.MODO_MANUAL, Time(0)))



    val irrigacaoStatus: irrigacao
        get() = _irrigacaoStatus.value

    var setores: List<Setor>
        get() = _setores
        set(value) {
            _setores.clear()
            _setores.addAll(value)
        }

    fun setBombaTime(hour: Int, minute: Int){
        _irrigacaoStatus.value.bombaTime = Time(((hour*3600000) + (minute*60000)).toLong())
        atualizarHorarios()
    }

    fun setIrriStatus(status: Int){

        if(status == 0) alterarModoManualSetor(0,true)

        _irrigacaoStatus.value = status.let {
            irrigacao(
                horaInicio = _irrigacaoStatus.value.horaInicio,
                horaFim = _irrigacaoStatus.value.horaFim,
                status = it,
                bombaTime = _irrigacaoStatus.value.bombaTime
            )
        }
    }

    fun getBombaTimeFormated(): String{
        return _irrigacaoStatus.value.bombaTime.toString().substring(0,5)
    }

    suspend fun modificarTimeSetor(hour: Int, minute: Int, index: Int): Boolean {
        atualizadorDeHorarios(hour, minute, index)
        atualizarHorarios()
        _setores[index].time = Time(((hour*3600000) + (minute*60000)).toLong()).toString().substring(0,5)

        return dataSource.salvarSetores(_setores);
    }

    private fun atualizadorDeHorarios(hour: Int, minute: Int, index: Int){
        var time = Time(((hour*3600000) + (minute*60000)).toLong())
        _setores[index].time = time.toString().substring(0,5)


        var timeBuffer: Time = _irrigacaoStatus.value.bombaTime

        for (i in 0..index - 1){
            val horasBuffer = _setores[i].time.substring(0,2).toInt()
            val minutosBuffer = _setores[i].time.substring(3,5).toInt()

            timeBuffer = Time(timeBuffer.time + (horasBuffer*3600000) + (minutosBuffer*60000))
        }

        _setores[index].horaLigar = timeBuffer.toString().substring(0,5)
        timeBuffer = Time(timeBuffer.time +  time.time)
        _setores[index].horaDesligar = timeBuffer.toString().substring(0,5)
    }

    private fun atualizarHorarios(){
        _irrigacaoStatus.value.horaInicio = _irrigacaoStatus.value.bombaTime.toString().substring(0,5)

        var timeBuffer: Time = _irrigacaoStatus.value.bombaTime
        for (i in 0 .. _setores.size - 1){
            if(_setores[i].time == "00:00") continue
            atualizadorDeHorarios(_setores[i].time.substring(0,2).toInt(), _setores[i].time.substring(3,5).toInt(), i)

            timeBuffer = Time(timeBuffer.time + _setores[i].time.substring(0,2).toInt()*3600000 + _setores[i].time.substring(3,5).toInt()*60000)
        }
        _irrigacaoStatus.value.horaFim = timeBuffer.toString().substring(0,5)
    }

    fun alterarModoManualSetor(index: Int, modo: Boolean){



        for (i in 0 .. _setores.size - 1){
            if(i == index){
                _setores[i] = _setores[i].copy(manualValue = modo)
                _setores[i] = _setores[i].copy(status = modo)
            } else {
                _setores[i] = _setores[i].copy(manualValue = false)
                _setores[i] = _setores[i].copy(status = false)
            }
        }

        if(!modo) {
            alterarModoManualSetor( 0,  true);
        }

    }
}

private fun getSetoresList() = List(5){ index ->
    Setor(
        time = "00:00",
        index = index,
        horaLigar = "00:00",
        horaDesligar = "00:00",
        status = false,
        manualValue = false
    )
}