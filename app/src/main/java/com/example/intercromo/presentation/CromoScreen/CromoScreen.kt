package com.example.intercromo.presentation.CromoScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.example.intercromo.model.Cromo

@Composable
fun PantallaCromo(controller: NavController, viewModel: CromoScreenViewModel){

    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val cromoNombre: String? = navBackStackEntry?.arguments?.getString("cromo")
    val cromo: Cromo? = viewModel.getCromo(cromoNombre)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ){

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Navigation Icon",
                    tint = Color.Black,
                    modifier = Modifier
                        .clickable {
                            controller.popBackStack()
                        }
                )
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite Icon",
                    tint = Color.Black
                    )
            }
            Spacer(modifier = Modifier.height(200.dp))


            if (cromo != null) {
                if (cromoNombre != null) {
                    Text(text = cromoNombre)
                }
            }

            AsyncImage(
                model = cromo?.imagen,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }

}