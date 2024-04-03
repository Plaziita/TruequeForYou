package com.example.intercromo.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.intercromo.model.Cromo
import com.example.intercromo.presentation.perfil.adquisiciones.AdquisicionesViewModel

@Composable
fun PantallaInicio(viewModel: AdquisicionesViewModel){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Restore,
                contentDescription = "Icono recientes",
                tint = Color.Black,
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Recientes",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
        }
        Recientes(viewModel)
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Icono favoritos",
                tint = Color.Black,
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Favoritos",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
        }
        Favoritos(viewModel)
        Spacer(modifier = Modifier.height(20.dp))
        MostrarCromos(viewModel)
    }
}

@Composable
fun ItemCromo(cromo: Cromo){
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

@Composable
fun Recientes(viewModel: AdquisicionesViewModel){

    val scrollState = rememberScrollState()
    var listaCromos = viewModel.listaCromos.value

    LazyRow(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .padding(start = 16.dp)
            .padding(end = 16.dp)
            .height(200.dp)
            .fillMaxWidth()
            .horizontalScroll(scrollState)
    ) {
        items(listaCromos){
            ItemCromo(cromo = it)
        }
    }

}

@Composable
fun Favoritos(viewModel: AdquisicionesViewModel){

    val scrollState = rememberScrollState()
    var listaCromos = viewModel.listaCromos.value

    LazyRow(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .padding(start = 16.dp)
            .padding(end = 16.dp)
            .height(200.dp)
            .fillMaxWidth()
            .horizontalScroll(scrollState)
    ) {
        items(listaCromos){
            ItemCromo(cromo = it)
        }
    }

}

@Composable
fun MostrarCromos(viewModel: AdquisicionesViewModel){

    var listaCromos = viewModel.listaCromos.value

    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ){
        items(listaCromos){
            ItemCromo(cromo = it)
        }
    }
}