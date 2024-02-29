package com.example.exa_recu_ricardogr.ui

import androidx.lifecycle.ViewModel
import com.example.examen1viewmodel.data.Asignatura
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState : StateFlow<GameUiState> = _uiState.asStateFlow()

    fun sumarHoras(asignatura: Asignatura, horas: String){
        var ultimaAccion: String
        var totalHoras = uiState.value.totalHoras
        var totalPrecio = uiState.value.totalPrecio
        var resumenArray = uiState.value.resumen

        var horasSumar = horas.toInt()

        if(horasSumar==0){
            ultimaAccion = "No se puede sumar con 0 horas"

            _uiState.update {
                it.copy(
                    ultimaAccion = ultimaAccion
                )
            }

            return
        }

        totalHoras += horasSumar
        totalPrecio += horasSumar*asignatura.precioHora
        var asignaturaBuscar = buscarMatriculacion(uiState.value.resumen,asignatura.nombre)
        if(asignaturaBuscar!=-1){
            resumenArray[asignaturaBuscar].horasMatriculadas += horasSumar
        }else{
            resumenArray.add(AsignaturasRegistradas(asignatura.nombre,asignatura.precioHora,horasSumar))
        }
        ultimaAccion = "Se han añadido "+horasSumar + " horas de "+asignatura.nombre+ " a " + asignatura.precioHora + " €"
        _uiState.update {
            it.copy(
                totalHoras = totalHoras,
                totalPrecio = totalPrecio,
                resumen = resumenArray,
                ultimaAccion = ultimaAccion
            )
        }
    }

    fun restarHoras(asignatura: Asignatura, horas: String){
        var ultimaAccion: String
        var totalHoras = uiState.value.totalHoras
        var totalPrecio = uiState.value.totalPrecio
        var resumenArray = uiState.value.resumen

        var horasRestar = horas.toInt()

        if(horasRestar==0){
            ultimaAccion = "No se puede restar con 0 horas"

            _uiState.update {
                it.copy(
                    ultimaAccion = ultimaAccion
                )
            }

            return
        }

        totalHoras -= horasRestar

        totalPrecio -= horasRestar*asignatura.precioHora
        var asignaturaBuscar = buscarMatriculacion(uiState.value.resumen,asignatura.nombre)
        if(asignaturaBuscar!=-1){
            if(resumenArray[asignaturaBuscar].horasMatriculadas< horasRestar){
                ultimaAccion = "No se puede restar mas horas de las que tienes"

                _uiState.update {
                    it.copy(
                        ultimaAccion = ultimaAccion
                    )
                }

                return
            }else {
                resumenArray[asignaturaBuscar].horasMatriculadas -= horasRestar
            }
        }else{
            ultimaAccion = "No puedes borrar horas si no estás matriculado"

            _uiState.update {
                it.copy(
                    ultimaAccion = ultimaAccion
                )
            }

            return
        }
        ultimaAccion = "Se han quitado "+horasRestar + " horas de "+asignatura.nombre+ " a " + asignatura.precioHora + " €"
        _uiState.update {
            it.copy(
                totalHoras = totalHoras,
                totalPrecio = totalPrecio,
                resumen = resumenArray,
                ultimaAccion = ultimaAccion
            )
        }
    }

    fun buscarMatriculacion(resumen: ArrayList<AsignaturasRegistradas>,asignaturaBuscar:String): Int{
        if(resumen.size!=0) {
            for (i in resumen.indices) {
                if (resumen[i].nombreAsignatura == asignaturaBuscar) {
                    return i
                }
            }
        }
        return -1
    }

    fun actualizarHoras(horas: String){
        _uiState.update {
            it.copy(
                horasAsignatura = horas
            )
        }
    }
}