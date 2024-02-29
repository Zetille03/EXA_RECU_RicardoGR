package com.example.exa_recu_ricardogr.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.examen1viewmodel.data.DataSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsignaturasScreen(gameViewModel: GameViewModel, accionNavigator: ()-> Unit){
    val gameUiState by gameViewModel.uiState.collectAsState()
    val asignaturas = DataSource.asignaturas

    Column {
        Text(
            "Bienvenido a la academia de Ricardo García",
            modifier = Modifier
                .background(Color.Gray)
                .fillMaxWidth()
                .padding(start = 20.dp, top = 20.dp)
        )
        LazyColumn(
            Modifier.height(250.dp)
        ) {
            items(asignaturas) {asignatura->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(250.dp)
                ) {
                    Text(
                        text = "Asig: " + asignatura.nombre,
                        Modifier
                            .background(Color.Yellow)
                            .fillMaxWidth()
                            .padding(20.dp)
                    )
                    Text(
                        text = "€/hora: " + asignatura.precioHora,
                        Modifier
                            .background(Color.Cyan)
                            .fillMaxWidth()
                            .padding(20.dp)
                    )
                    Row(horizontalArrangement = Arrangement.Center) {
                        Button(
                            onClick = {
                                      gameViewModel.sumarHoras(asignatura,gameUiState.horasAsignatura)
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 50.dp)
                        ){
                            Text("+")
                        }
                        Button(
                            onClick = {
                                gameViewModel.restarHoras(asignatura,gameUiState.horasAsignatura)
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 50.dp)
                        ){
                            Text("-")
                        }
                    }
                }
            }
        }
        TextField(
            value = gameUiState.horasAsignatura,
            onValueChange = { gameViewModel.actualizarHoras(it) },
            label = { Text("Horas a contratar o a eliminar") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Column(
            modifier = Modifier
                .background(Color.Gray)
                .fillMaxWidth()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier
                    .background(Color.Yellow)
                    .fillMaxWidth()) {
                Text("Ultima accion:\n " + gameUiState.ultimaAccion)
            }
            Box(
                Modifier
                    .background(Color.White)
                    .fillMaxWidth()
            ){
                Text("Resumen: ")
            }
            if(gameUiState.resumen.size==0){
                Box(
                    Modifier
                        .background(Color.White)
                        .fillMaxWidth()){
                    Text(" No hay nada que mostrar (defecto)")
                }
            }else{
                for(a in gameUiState.resumen){
                    Box(
                        Modifier
                            .background(Color.White)
                            .fillMaxWidth()){
                        Text("Asig: "+a.nombreAsignatura + " precio/horas " + a.precioHora + " total horas: " + a.horasMatriculadas )
                    }
                }
            }
            Box(
                Modifier
                    .background(Color.Yellow)
                    .fillMaxWidth()){

                Text("Total horas: " + gameUiState.totalHoras)

            }
            Box(
                Modifier
                    .background(Color.Yellow)
                    .fillMaxWidth()){
                Text("Total precio: "+gameUiState.totalPrecio)
            }
            
        }
        Button(
            onClick =  accionNavigator ,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Cambiar de pantalla")
        }
    }

}

@Composable
fun PantallaVacia(accionNavigator: () -> Unit){
    Column {
        Text("Soy una pantalla vacia")
        Button(
            onClick = accionNavigator,
            content = { Text("Volver") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}