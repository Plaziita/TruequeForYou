
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberImagePainter
import com.example.intercromo.model.Cromo
import com.example.intercromo.navigation.rutaIntercambios.VentanasIntercambios
import com.example.intercromo.navigation.rutaPerfil.VentanasPerfil

@Composable
fun PantallaCromo(controller: NavController, viewModel: CromoScreenViewModel) {
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val cromoId: String? = navBackStackEntry?.arguments?.getString("cromo")
    val cromo: Cromo? = viewModel.getCromo(cromoId)
    val cromoUserId = cromo?.idUsuario.toString()
    val currentUser = viewModel.currentUserID
    val context = LocalContext.current

    var isFavorite = false
    var estado by remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }

    // Actualizar el estado del favorito al cargar la pantalla
    /*LaunchedEffect(cromoId) {
        isFavorite = viewModel.isFavorite(cromoId)
    }*/

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(6.dp)
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
            if (cromo?.idUsuario != currentUser) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite Icon",
                    tint = if (isFavorite) Color.Red else Color.Black,
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            isFavorite = !isFavorite
                            viewModel.updateFavoriteStatus(cromoId)
                        }
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            if (cromo?.idUsuario == currentUser) {
                Box(modifier = Modifier.size(35.dp)) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Mostrar opciones",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(35.dp)
                            .clickable {
                                estado = !estado
                            }
                    )
                    DropdownMenu(
                        expanded = estado,
                        onDismissRequest = { estado = false },
                        modifier = Modifier
                            .background(Color.White)
                            .width(175.dp)
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                controller.navigate(
                                    "${VentanasPerfil.EditarCromoScreen.ruta.replace(
                                        "{cromoId}",
                                        cromo.cromoId
                                    ).replace(
                                        "{descripcion}",
                                        cromo.descripcion
                                    ).replace(
                                        "{nombre}",
                                        cromo.nombre
                                    ).replace(
                                        "{categoria}",
                                        cromo.categoria
                                    )}"
                                )
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Mostrar opciones",
                                tint = Color.Black,
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Editar cromo", color = Color.Black)
                        }

                        DropdownMenuItem(
                            onClick = {
                                showDialog.value = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Mostrar opciones",
                                tint = Color.Black,
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Eliminar cromo", color = Color.Black)
                        }

                    }
                }
            }
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
        if (cromo != null) {
            Text(
                text = cromo.categoria,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFA500),
                modifier = Modifier.alpha(0.8f),
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Carta / Cromo",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (cromo != null) {
            Text(
                text = cromo.nombre,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 24.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (cromo != null) {
            Text(
                text = cromo.descripcion,
                color = Color.Black,
                textAlign = TextAlign.Justify,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        if (cromo?.idUsuario != currentUser) {
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
                        controller.navigate(
                            "${VentanasIntercambios.SeleccionarCromoScreen.ruta.replace(
                                "{idEmisor}",
                                currentUser
                            ).replace(
                                "{idRemitente}",
                                cromoUserId
                            ).replace(
                                "{idCromoRemitente}",
                                cromo!!.cromoId
                            )}"
                        )
                    }
                ) {
                    Text(
                        text = "Iniciar intercambio",
                        fontSize = 18.sp
                    )
                }
            }
        }
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    showDialog.value = false
                },
                title = { Text("¿Quieres eliminar este cromo?") },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.deleteCromo(cromo?.cromoId ?: "")
                            showDialog.value = false
                            Toast.makeText(context, "Cromo eliminado correctamente!", Toast.LENGTH_SHORT).show()
                            controller.popBackStack()
                        }
                    ) {
                        Text("Eliminar")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            showDialog.value = false
                        }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}
