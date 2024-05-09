import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberImagePainter
import com.example.intercromo.model.Cromo
import com.example.intercromo.presentation.CromoScreen.CromoScreenViewModel

@Composable
fun PantallaCromo(controller: NavController, viewModel: CromoScreenViewModel) {
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val cromoNombre: String? = navBackStackEntry?.arguments?.getString("cromo")
    val cromo: Cromo? = viewModel.getCromo(cromoNombre)
    val cromoUserId = cromo?.idUsuario.toString()
    val currentUser = viewModel.userId.toString()

    var isFavorite by remember { mutableStateOf(viewModel.isFavorite(cromoNombre)) }

    // Actualizar el estado del favorito al cargar la pantalla
    LaunchedEffect(cromoNombre) {
        isFavorite = viewModel.isFavorite(cromoNombre)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
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
            Spacer(modifier = Modifier.weight(1f))
            //cambiar icono de favorito
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Favorite Icon",
                tint = if (isFavorite) Color.Red else Color.Black,
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        isFavorite = !isFavorite
                        viewModel.updateFavoriteStatus(cromoNombre, isFavorite)
                    }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        cromo?.imagen?.let { imageUrl ->
            Image(
                painter = rememberImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(400.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = cromo?.categoria ?: "",
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFA500),
            modifier = Modifier.alpha(0.8f),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Carta / Cromo",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = cromo?.nombre ?: "",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = cromo?.descripcion ?: "",
            color = Color.Black,
            textAlign = TextAlign.Justify,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom, // Alinear los elementos hacia abajo
            horizontalAlignment = Alignment.CenterHorizontally // Centrar horizontalmente
        ) {
            Button(
                modifier = Modifier
                    .alpha(0.8f)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFA500),
                    contentColor = Color.Black
                ),
                onClick = {
                    viewModel.startConversation(cromoUserId, currentUser)
                }
            ) {
                Text(
                    text = "Iniciar conversación",
                    fontSize = 18.sp
                )
            }
        }
    }
}
