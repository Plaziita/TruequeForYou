
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun PantallaTransferencias(navController: NavController){
    Scaffold(
        topBar = {
            BarraSuperiorTransferencias(navController)
        }
    ) {
        Transferencias()
    }
}

@Composable
fun Transferencias() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        NavigationBar()
        Text(
            text = "Historial"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorTransferencias(navController: NavController) {
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
                    text = "Transferencias"
                )
            }
        }
    )
}

@Composable
fun NavigationBar() {
    var selectedTab by remember { mutableStateOf(BottomNavItems.enProceso) }

    Column {
        TabRow(
            selectedTabIndex = selectedTab.ordinal,
            contentColor = Color.Transparent,
            modifier = Modifier
                .padding(8.dp)
        ) {
            BottomNavItems.values().forEach { tab ->
                Tab(
                    text = {
                        Text(
                            text = tab.title,
                            color = Color(0xFFFFA500),
                            )
                    },
                    selected = selectedTab == tab,
                    onClick = {
                        selectedTab = tab
                    },
                    modifier = Modifier
                        .background(
                            color = Color.Black,
                            shape = RoundedCornerShape(60.dp)
                        )
                )
            }
        }

        when (selectedTab) {
            BottomNavItems.enProceso -> {
                PantallaEnCurso()
            }
            BottomNavItems.Finalizado -> {
                PantallaFinalizado()
            }
        }
    }
}

enum class BottomNavItems(val title: String) {
    enProceso("En curso"),
    Finalizado("Finalizadas")
}

@Composable
fun PantallaEnCurso(){

}

@Composable
fun PantallaFinalizado(){

}

@Preview
@Composable
fun PantallaTransferenciasPreview(){
    PantallaTransferencias(navController = rememberNavController())
}
