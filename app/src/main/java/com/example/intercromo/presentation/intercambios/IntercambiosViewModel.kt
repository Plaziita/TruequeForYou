
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.dao.IntercambiosRepository
import com.example.intercromo.dao.UsuarioRepository
import com.example.intercromo.model.Cromo
import com.example.intercromo.model.Intercambios
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IntercambiosViewModel(val repository: IntercambiosRepository,val cromoRepository: CromoRepository, val usuarioRepository: UsuarioRepository) : ViewModel() {

   // private val _intercambios = MutableStateFlow<List<Intercambios>>(emptyList())
   // val intercambios: StateFlow<List<Intercambios>> get() = _intercambios

    val intercambios: MutableState<List<Intercambios>> = mutableStateOf(listOf())
    val cromos: MutableState<List<Cromo>> = mutableStateOf(listOf())
    val estado: MutableState<String> = mutableStateOf("")
    val nombreRemitente: MutableState<String?> = mutableStateOf("")

    //private val _cromos = MutableStateFlow<List<Cromo>>(emptyList())
    //val cromos: StateFlow<List<Cromo>> get() = _cromos

    init {
        cargarIntercambios()
    }

    private fun cargarIntercambios() {
        viewModelScope.launch {
            val (intercambiosList, cromoIdsList) = repository.getIntercambiosByUserId()
            intercambios.value = intercambiosList
            val cromosList = cromoRepository.getCromosById(cromoIdsList)
            cromos.value = cromosList
        }
    }

     fun realizarIntercambio(cromoEmisor: Cromo, cromoRemitente: Cromo) {


        CoroutineScope(Dispatchers.IO).launch {
            cromoRepository.intercambiarCromos(cromoEmisor, cromoRemitente)
        }
    }

    fun cargarNombre(userId: String, onNombreObtenido: (String?) -> Unit) {
        viewModelScope.launch {
            val nombre = usuarioRepository.getNombre(userId)
            onNombreObtenido(nombre)
        }
    }

    fun nombreLocal(): String? {
        return usuarioRepository.getNombreUsuario()
    }

    fun updateIntercambio(intercambioId: String, accion: String) {
        viewModelScope.launch {
            try {
                repository.updateIntercambio(intercambioId, accion)
            } catch (e: Exception) {
                // Manejar cualquier error

            }
        }
    }

    fun updateEstado(cromoId:String){
        val listaInter = intercambios.value.toMutableList() // Convertir a lista mutable
        listaInter.removeIf { it.idIntercambio == cromoId } // Eliminar el intercambio de la lista mutable
        intercambios.value = listaInter.toList()
    }



}
