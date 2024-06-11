package com.example.intercromo.presentation.favoritos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.intercromo.presentation.inicio.ItemCromo
import kotlinx.coroutines.delay

@Composable
fun PantallaFavoritos(favoritosViewModel: FavoritosViewModel, navController: NavController){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item{
            MostrarFavoritos(favoritosViewModel, navController)
        }
    }
}

@Composable
fun MostrarFavoritos(favoritosViewModel: FavoritosViewModel, navController: NavController) {
    var listaCromos = favoritosViewModel.listaCromos.value
    var isLoading by remember { mutableStateOf(false) }

    // Calcular la altura necesaria para LazyVerticalGrid
    val gridItemHeight = 300.dp // Altura de cada item en el grid
    val columnSize = 2 // Número de columnas
    val rows = (listaCromos.size + columnSize - 1) / columnSize
    val gridHeight = (rows * gridItemHeight.value).dp // Altura total del grid

    LaunchedEffect(key1 = true) {
        delay(2000)
        isLoading = true
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Icono",
            tint = Color.Black,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Favoritos",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 20.sp
        )
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columnSize),
        modifier = Modifier
            .height(gridHeight)
            .padding(8.dp)
    ) {
        items(listaCromos) {
            ItemCromo(cromo = it, navController)
        }
    }
}

