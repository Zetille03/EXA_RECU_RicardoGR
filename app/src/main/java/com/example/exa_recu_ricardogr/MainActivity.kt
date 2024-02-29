package com.example.exa_recu_ricardogr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exa_recu_ricardogr.ui.AsignaturasScreen
import com.example.exa_recu_ricardogr.ui.GameViewModel
import com.example.exa_recu_ricardogr.ui.PantallaVacia
import com.example.exa_recu_ricardogr.ui.theme.EXA_RECU_RicardoGRTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EXA_RECU_RicardoGRTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main()
                }
            }
        }
    }
}

enum class Pantallas(@StringRes titulo: Int){
    PantallaLoterias(titulo = R.string.p1),
    PantallaVacia(titulo = R.string.p2)
}

@Composable
fun Main(navController: NavHostController = rememberNavController(),gameViewModel: GameViewModel = viewModel()){
    NavHost(
        navController = navController,
        startDestination = Pantallas.PantallaLoterias.name
    ){
        composable(route = Pantallas.PantallaLoterias.name){
            AsignaturasScreen(gameViewModel = gameViewModel, accionNavigator = { navController.navigate(Pantallas.PantallaVacia.name) })
        }
        composable(route = Pantallas.PantallaVacia.name){
            PantallaVacia(accionNavigator = {
                navController.navigate(Pantallas.PantallaLoterias.name)
            })
        }

    }
}

