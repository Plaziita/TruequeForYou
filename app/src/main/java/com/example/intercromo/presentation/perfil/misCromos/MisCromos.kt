package com.example.intercromo.presentation.perfil.misCromos

import android.util.Log
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.intercromo.model.Cromo
import com.example.intercromo.navigation.rutaInicio.VentanasInicio

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaMisCromos(controller: NavController, viewModel: MisCromosViewModel) {
    var listaCromos = viewModel.listaCromos.value
    Log.e("lista",listaCromos.toString())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Navigation Icon",
                tint = Color.Black,
                modifier = Modifier
                    .clickable {
                        controller.popBackStack()
                    }
                    .size(35.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Mis Cromos",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.background(Color.White)
        ) {
            items(listaCromos) {
                MisCromos(cromo = it,controller)
            }
        }
    }
}



@Composable
fun MisCromos(cromo: Cromo,controller: NavController) {
    Card(
        modifier = Modifier
            .size(200.dp, 300.dp)
            .padding(16.dp)
            .clickable {
                controller.navigate(
                    "${
                        VentanasInicio.CromoScreen.ruta.replace(
                            "{cromo}",
                            cromo.cromoId
                        )
                    }"
                )
            }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {
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

