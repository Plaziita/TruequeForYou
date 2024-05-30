package com.example.intercromo.presentation.intercambios

import IntercambiosViewModel
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirlineStops
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.intercromo.R
import com.example.intercromo.model.Cromo
import com.example.intercromo.model.Intercambios
import com.example.intercromo.valoracion.RatingBar

@Composable
fun PantallaIntercambios(intercambiosViewModel: IntercambiosViewModel) {
    //val intercambios by intercambiosViewModel.intercambios.collectAsState()
    //val cromos by intercambiosViewModel.cromos.collectAsState()

    val intercambios = intercambiosViewModel.intercambios.value
    val cromos = intercambiosViewModel.cromos.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Icono",
                tint = Color.Black,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Intercambios",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        MostrarIntercambios(intercambios, cromos, intercambiosViewModel)
    }
}

@Composable
fun MostrarIntercambios(
    intercambios: List<Intercambios>,
    cromos: List<Cromo>,
    intercambiosViewModel: IntercambiosViewModel
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 40.dp) // Agregar relleno en la parte inferior
    ) {
        items(intercambios) { intercambio ->
            val cromoEmisor = cromos.find { it.cromoId == intercambio.idCromoEmisor }
            val cromoRemitente = cromos.find { it.cromoId == intercambio.idCromoRemitente }
            if (cromoEmisor != null && cromoRemitente != null) {
                ItemIntercambio(
                    intercambio = intercambio,
                    cromoEmisor = cromoEmisor,
                    cromoRemitente = cromoRemitente,
                    intercambiosViewModel
                )
                Spacer(modifier = Modifier.height(16.dp))
            } else {
                Log.e("MostrarIntercambios", "Cromo no encontrado para intercambio: $intercambio")
            }
        }
    }
}


@Composable
fun ItemIntercambio(
    intercambio: Intercambios,
    cromoEmisor: Cromo,
    cromoRemitente: Cromo,
    intercambiosViewModel: IntercambiosViewModel
) {

    var rating by remember { mutableStateOf(2.5) }
    var nombreEmisor by remember { mutableStateOf("") }

    val rechazar = "Rechazar"
    val aceptar = "Aceptar"

    // Obtener el nombre del usuario emisor
    LaunchedEffect(key1 = intercambio.idUserEmisor, key2 = intercambio.idUserRemitente) {
        intercambiosViewModel.cargarNombre(intercambio.idUserEmisor) { nombre ->
            // Actualizar el nombre del usuario en el estado
            nombre?.let {
                nombreEmisor = it
            }
        }


    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(modifier = Modifier.padding(8.dp)) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = nombreEmisor,
                                color = Color.Black
                            )
                            RatingBar(
                                modifier = Modifier
                                    .size(8.dp),
                                rating = rating,
                                onRatingChanged = {
                                    rating = it
                                },
                                starsColor = Color.Black
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    AsyncImage(
                        model = cromoEmisor.imagen,
                        contentDescription = null,
                        modifier = Modifier.size(110.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        modifier = Modifier
                            .size(110.dp, 35.dp)
                            .alpha(0.8f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFA500),
                            contentColor = Color.Black
                        ),
                        onClick = {
                            intercambiosViewModel.realizarIntercambio(cromoEmisor, cromoRemitente)
                            intercambiosViewModel.updateIntercambio(intercambio.idIntercambio, aceptar )
                        }
                    ) {
                        Text(
                            text = aceptar,
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.AirlineStops,
                        contentDescription = "Icono",
                        tint = Color.Black,
                        modifier = Modifier.size(60.dp)
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            intercambiosViewModel.nombreLocal()?.let {
                                Text(
                                    text = it,
                                    color = Color.Black
                                )
                            }
                            RatingBar(
                                modifier = Modifier
                                    .size(8.dp),
                                rating = 4.0,
                                onRatingChanged = {
                                    rating = it
                                },
                                starsColor = Color.Black
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    AsyncImage(
                        model = cromoRemitente.imagen,
                        contentDescription = null,
                        modifier = Modifier.size(110.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        modifier = Modifier
                            .size(110.dp, 35.dp)
                            .alpha(0.8f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFA500),
                            contentColor = Color.Black
                        ),
                        onClick = {
                            intercambiosViewModel.updateIntercambio(intercambio.idIntercambio, rechazar )
                        }
                    ) {
                        Text(
                            text = rechazar,
                        )
                    }
                }

            }
        }
    }
}
