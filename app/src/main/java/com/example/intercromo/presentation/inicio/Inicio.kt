package com.example.intercromo.presentation.inicio

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.intercromo.model.Cromo
import com.example.intercromo.navigation.rutaInicio.VentanasInicio
import kotlinx.coroutines.delay

@Composable
fun PantallaInicio(viewModel: InicioViewModel, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        SearchBar(navController, viewModel)
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            item {
                Recientes(viewModel, navController)
            }
            item {
                MostrarCromos(viewModel, navController)
            }
        }
    }
}


@Composable
fun SearchBar(navController: NavController, viewModel: InicioViewModel) {
    val query = viewModel.query.value
    val cromosFiltrados = viewModel.cromosFiltrados.value
    var mensaje = ""

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        shape = RoundedCornerShape(16.dp),
    ) {
        TextField(
            value = query,
            onValueChange = {
                viewModel.queryChanged(it)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Buscar cromos...", color = Color.Black)
            },
            leadingIcon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Search...",
                    tint = Color.Black,
                    modifier = Modifier
                        .clickable {
                            viewModel.cromosFiltrados
                            navController.navigate(
                                "${
                                    VentanasInicio.CromoFiltradoScreen.ruta.replace(
                                        "{query}",
                                        query
                                    )
                                }"
                            )
                        }
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    mensaje = query
                    viewModel.cromosFiltrados
                    navController.navigate(
                        "${
                            VentanasInicio.CromoFiltradoScreen.ruta.replace(
                                "{query}",
                                query
                            )
                        }"
                    )
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Gray,
                placeholderColor = Color.Black,
            )
        )
    }
}


@Composable
fun ItemCromo(cromo: Cromo, navController: NavController) {

    Card(
        modifier = Modifier
            .size(200.dp, 300.dp)
            .padding(16.dp)
            .clickable {
                navController.navigate(
                    "${
                        VentanasInicio.CromoScreen.ruta.replace(
                            "{cromo}",
                            cromo.cromoId
                        )
                    }"
                )
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                AsyncImage(
                    model = cromo.imagen,
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(Color.Black)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Autorenew,
                        contentDescription = "Icono tradear",
                        tint = Color.Black,
                    )
                    Text(
                        text = cromo.categoria,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFFA500),
                        modifier = Modifier.alpha(0.8f)
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
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun Recientes(viewModel: InicioViewModel, navController: NavController) {

    //val scrollState = rememberScrollState()
    var listaCromos = viewModel.cromosRecientes.value
    if (listaCromos.size != 0) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Restore,
                contentDescription = "Icono recientes",
                tint = Color.Black,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Recientes",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Icono",
                tint = Color.Black,
                modifier = Modifier.size(40.dp)
            )
        }

        LazyRow(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .padding(start = 8.dp)
                .padding(end = 8.dp)
                .fillMaxWidth()
        ) {
            items(listaCromos) {
                ItemCromo(cromo = it, navController)
            }
        }
    }else{
        //do nothing
    }


}

@Composable
fun MostrarCromos(viewModel: InicioViewModel, navController: NavController) {
    var listaCromos = viewModel.listaCromos.value
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        delay(2000)
        isLoading = true
    }

    // Calcular la altura necesaria para LazyVerticalGrid
    val gridItemHeight = 300.dp // Altura de cada item en el grid
    val columnSize = 2 // Número de columnas
    val rows = (listaCromos.size + columnSize - 1) / columnSize
    val gridHeight = (rows * gridItemHeight.value).dp // Altura total del grid

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowDownward,
                contentDescription = "Icono",
                tint = Color.Black,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Todos los cromos",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(columnSize),
            modifier = Modifier
                .height(gridHeight)
        ) {
            items(listaCromos) {
                ItemCromo(cromo = it, navController)
            }
        }

        if (isLoading) {
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "-No hay más cromos en este momento-",
                color = Color.Gray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}