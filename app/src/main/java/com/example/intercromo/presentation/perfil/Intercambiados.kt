
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

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
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorIntercambiados(navController: NavController) {
    TopAppBar(
        modifier = Modifier.background(Color.White),
        title = {
            Row {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Navigation Icon",
                    tint = Color.Black,
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
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

