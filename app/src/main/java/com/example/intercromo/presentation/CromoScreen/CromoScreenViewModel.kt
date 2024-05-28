
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.dao.IntercambiosRepository
import com.example.intercromo.dao.UsuarioRepository
import com.example.intercromo.model.Cromo
import kotlinx.coroutines.launch

class CromoScreenViewModel(cromoRepository: CromoRepository, repository: IntercambiosRepository, usuarioRepository: UsuarioRepository) : ViewModel() {

    val cromoRepository = cromoRepository
    val repository = repository
    val usuarioRepository = usuarioRepository

    val currentUserID = usuarioRepository.currentUser!!.uid

    fun getCromo(nombre_: String?): Cromo? {
        return cromoRepository.getCromo(nombre_)
    }

    fun updateFavoriteStatus(cromoNombre: String?) {
        if (cromoNombre != null) {
            usuarioRepository.updateFavoritos(cromoNombre)
        }
    }

    fun deleteCromo(cromoId: String?) {
        viewModelScope.launch {
            cromoRepository.deleteCromo(cromoId)
        }
    }

    fun startIntercambio(user1: String, user2: String, cromoId: String?) {
        val idEmisor = user1
        val idRemitente = user2
        val idCromoRemitente = cromoId
        //???

    }

}
