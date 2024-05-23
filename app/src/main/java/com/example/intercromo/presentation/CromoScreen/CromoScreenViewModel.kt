
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intercromo.dao.ChatRepository
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.dao.UsuarioRepository
import com.example.intercromo.model.Cromo
import kotlinx.coroutines.launch

class CromoScreenViewModel(cromoRepository: CromoRepository, repository: ChatRepository,usuarioRepository: UsuarioRepository) : ViewModel() {

    val cromoRepository = cromoRepository
    val repository = repository
    val usuarioRepository = usuarioRepository
    private var _isFavoriteMap: MutableMap<String, Boolean> = mutableMapOf()

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

    fun isFavorite(nombre_: String?): Boolean {
        return usuarioRepository.isFavorite(nombre_)
    }

    fun startConversation(user1: String, user2: String) {
        repository.startConversation(user1, user2)
    }

}
