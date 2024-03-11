

package com.example.intercromo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.intercromo.R
import com.example.intercromo.navigation.ventanasRegistro.VentanasLogIn

@Composable
fun BienvenidoScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
       Image(
            painter = painterResource(id = R.drawable.fondoinicio),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Te aguardabamos con ansias,",
                fontSize = 28.sp,
                color = Color(0xFF077B22)
                )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "!Unete a nosotros!",
                fontSize = 40.sp,
                color = Color(0xFFFFA500)
                )
            Spacer(modifier = Modifier.height(20.dp))
            botonesRegistro(navController)
            Spacer(modifier = Modifier.height(20.dp))
            TextoInicioSesion(navController)
        }
    }
}

@Composable
fun TextoInicioSesion(navController: NavController) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
            append("Puedes ")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline, color = Color(0xFFFFA500))) {
            append("Iniciar sesion")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
            append(" aqui con tu correo electronico!")
        }
    }
    Text(
        text = text,
        fontSize = 20.sp,
        color = Color(0xFFFFA500),
        modifier = Modifier.clickable {
            navController.navigate(VentanasLogIn.LoginEmailScreen.ruta)
        }
    )
}

@Composable
fun botonesRegistro(navController: NavController){
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = {
        }
    ) {
        Image(
            painter = painterResource(id = R.drawable.google),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Google",
            fontWeight = FontWeight.Bold
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = {
            navController.navigate(VentanasLogIn.EmailScreen.ruta)
        }
    ) {
        Image(
            painter = painterResource(id = R.drawable.email),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Correo electronico",
            fontWeight = FontWeight.Bold
        )
    }
}



@ExperimentalMaterial3Api
@Composable
@Preview
fun BienvenidoPreview() {
    BienvenidoScreen(rememberNavController())
}