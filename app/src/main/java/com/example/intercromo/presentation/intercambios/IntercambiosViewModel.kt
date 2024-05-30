
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.dao.IntercambiosRepository
import com.example.intercromo.model.Cromo
import com.example.intercromo.model.Intercambios
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IntercambiosViewModel(val repository: IntercambiosRepository,val cromoRepository: CromoRepository) : ViewModel() {

    private val _intercambios = MutableStateFlow<List<Intercambios>>(emptyList())
    val intercambios: StateFlow<List<Intercambios>> get() = _intercambios

    private val _cromos = MutableStateFlow<List<Cromo>>(emptyList())
    val cromos: StateFlow<List<Cromo>> get() = _cromos

    init {
        cargarIntercambios()
    }

    private fun cargarIntercambios() {
        viewModelScope.launch {
            val (intercambiosList, cromoIdsList) = repository.getIntercambiosByUserId()
            _intercambios.value = intercambiosList
            val cromosList = cromoRepository.getCromosById(cromoIdsList)
            _cromos.value = cromosList
        }
    }
}
