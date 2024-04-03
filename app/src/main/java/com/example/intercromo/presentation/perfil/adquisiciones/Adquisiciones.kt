package com.example.intercromo.presentation.perfil.adquisiciones

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.intercromo.model.Cromo
import com.example.intercromo.navigation.rutaPerfil.VentanasPerfil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAdquisiciones(navController: NavController, viewModel: AdquisicionesViewModel) {
    //var listaCromos by remember { mutableStateOf(listOf<Cromo>()) }
    var listaCromos = viewModel.listaCromos.value

    Scaffold(
        topBar = {
            BarraSuperior(navController)
        }
    ) { contentPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = contentPadding,
        ) {
           // Datasource_Cromo().getCromos { listaCromos = it }
            items(listaCromos){
               Adquisiciones(cromo = it)
           }
        }
    }
}



@Composable
fun Adquisiciones(cromo: Cromo){
    Card(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .size(200.dp, 300.dp)
            .padding(16.dp)
            .background(Color.White)
    ) {
        Column (modifier = Modifier.padding(8.dp)){
            AsyncImage(
                model = cromo.imagen,
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(Color.Black)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Default.Autorenew,
                    contentDescription = "Icono tradear",
                    tint = Color.Black,
                )
                Text(
                    text = cromo.categoria,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Icono favorito",
                    tint = Color.Black,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Carta / Cromo",
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = cromo.nombre,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior(navController: NavController) {
    TopAppBar(
        title = {
            Row {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Navigation Icon",
                    tint = Color.Black,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(VentanasPerfil.PerfilScreen.ruta)
                        }
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "Adquisiciones"
                )
            }
        }
    )
}


