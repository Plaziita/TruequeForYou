package com.example.intercromo.presentation.perfil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.intercromo.R
import com.example.intercromo.dao.UsuarioRepository
import com.example.intercromo.navigation.rutaPerfil.BottomBarPerfil
import com.example.intercromo.navigation.rutaPerfil.VentanasPerfil
import com.example.intercromo.valoracion.RatingBar

@Composable
fun PantallaPerfil(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        DatosUsuario(navController)
        Spacer(modifier = Modifier.height(20.dp))
        Intercambios(navController)
        Configuracion()
        BotonCerrarSesion(navController)

    }
}

@Composable
fun Intercambios(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color(0xFFFFA500)),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Intercambios",
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
    OpcionesIntercambios(navController)
}

@Composable
fun Configuracion(){

    val barraAbajo: BottomBarPerfil = viewModel()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color(0xFFFFA500)),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Configuración",
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
    OpcionesConfiguracion(barraAbajo)
    BarraAbajoPerfil(barraAbajo)
}

@Composable
fun BotonCerrarSesion(navController: NavController){

    val context = LocalContext.current
    val auth = UsuarioRepository(navController)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier
                .alpha(0.8f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFA500),
                contentColor = Color.Black
            ),
            onClick = {
                auth.cerrarSesion(context)
            }
        ) {
            Text(
                text = "Cerrar sesión",
                fontSize = 18.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraAbajoPerfil(barraAbajo: BottomBarPerfil){
    if (barraAbajo.mostrarBarra){
        ModalBottomSheet(
            onDismissRequest = { barraAbajo.mostrarBarra = false }
        ) {
            ContentBottomSheet(barraAbajo)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentBottomSheet(barraAbajo: BottomBarPerfil) {
    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre y apellidos") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            leadingIcon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        )
        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Nº de Telefono") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            leadingIcon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        )
        Button(
            modifier = Modifier
                .alpha(0.8f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFA500),
                contentColor = Color.Black
            ),
            onClick = {
                barraAbajo.mostrarBarra = false
            }
        ) {
            Text(
                text = "Guardar cambios",
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun OpcionesConfiguracion(barraAbajo: BottomBarPerfil){
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(8.dp)
                .clickable {
                    barraAbajo.mostrarBarra = true
                },
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Navigation Icon",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Editar perfil",
                color = Color.Black,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Navigation Icon",
                tint = Color.Black
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Navigation Icon",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Dirección de envio",
                color = Color.Black,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Navigation Icon",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun OpcionesIntercambios(navController: NavController){
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(8.dp)
                .clickable {
                    navController.navigate(VentanasPerfil.AdquisicionesScreen.ruta)
                },
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.ArrowUpward,
                contentDescription = "Navigation Icon",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Adquisiciones",
                color = Color.Black,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Navigation Icon",
                tint = Color.Black
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(8.dp)
                .clickable {
                    navController.navigate(VentanasPerfil.TransferenciasScreen.ruta)
                },
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Navigation Icon",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Transferencias",
                color = Color.Black,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Navigation Icon",
                tint = Color.Black
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(8.dp)
                .clickable {
                    navController.navigate(VentanasPerfil.HistorialScreen.ruta)
                },
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.History,
                contentDescription = "Navigation Icon",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Historial",
                color = Color.Black,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Navigation Icon",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun DatosUsuario(navController: NavController){


    val auth = UsuarioRepository(navController)


    val userProfileImageUrl = auth.getUserProfileImageUrl()
    var rating by remember { mutableStateOf(2.5) }

    if (auth.currentUser != null) {
        Column(
            modifier = Modifier
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (userProfileImageUrl != null) {
                    Image(
                        painter = rememberImagePainter(userProfileImageUrl),
                        contentDescription = "Imagen del usuario",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(75.dp)
                            .clip(CircleShape)
                    )
                }else{
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Imagen predeterminada",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(75.dp)
                            .clip(CircleShape)
                    )
                }
                Column() {
                    Spacer(modifier = Modifier.height(8.dp))
                    if(auth.currentUser != null) {
                        Text(
                            text = "${auth.getNombreUsuario()}",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp
                        )
                    }else{
                        Text(
                            text = "${auth.getNombreUsuario()}",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp
                        )
                    }
                    RatingBar(
                        modifier = Modifier
                            .size(25.dp),
                        rating = rating,
                        onRatingChanged = {
                            rating = it
                        },
                        starsColor = Color.Black
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(35.dp))
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Navigation Icon",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}