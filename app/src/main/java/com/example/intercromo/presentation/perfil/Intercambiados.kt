
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.intercromo.navigation.rutaPerfil.VentanasPerfil

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaIntercambios(navController: NavController){
    Scaffold(
        topBar = {
            BarraSuperiorIntercambiados(navController)
        }
    ) {
        Intercambios()
    }
}

@Composable
fun Intercambios() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorIntercambiados(navController: NavController) {
    TopAppBar(
        title = {
            Row {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Navigation Icon",
                    tint = Color.Black,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(VentanasPerfil.PerfilScreen.ruta)
                        }
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "Intercambios"
                )
            }
        }
    )
}

@Preview
@Composable
fun PantallaIntercambiadosPreview(){
    PantallaIntercambios(navController = rememberNavController())
}
