package com.example.intercromo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.intercromo.navigation.manejadorRutas.ManejadorRutas
import com.example.intercromo.ui.theme.TruequeForYouTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TruequeForYouTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ManejadorRutas(navController = rememberNavController())
                }
            }
        }
    }
}

@Composable
@Preview
fun PantallaMainPreview(){
    ManejadorRutas(navController = rememberNavController())
}
