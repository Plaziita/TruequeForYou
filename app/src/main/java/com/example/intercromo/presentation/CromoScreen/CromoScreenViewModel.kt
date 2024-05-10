import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intercromo.dao.ChatRepository
import com.example.intercromo.dao.CromoRepository
import com.example.intercromo.model.Cromo
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class CromoScreenViewModel(cromoRepository: CromoRepository, repository: ChatRepository) : ViewModel() {

    val cromoRepository = cromoRepository
    val repository = repository
    val userId = Firebase.auth.currentUser?.uid
    private var _isFavoriteMap: MutableMap<String, Boolean> = mutableMapOf()

    fun getCromo(nombre_: String?): Cromo? {
        return cromoRepository.getCromo(nombre_)
    }

    fun updateFavoriteStatus(cromoNombre: String?, isFavorite: Boolean) {
        if (!cromoNombre.isNullOrBlank() && userId != null) {
            viewModelScope.launch {
                cromoRepository.updateFavoriteStatus(cromoNombre, isFavorite, userId)
            }
        }
    }

    fun deleteCromo(cromoId: String?) {
        viewModelScope.launch {
            cromoRepository.deleteCromo(cromoId)
        }
    }

    fun isFavorite(nombre_: String?): Boolean {
        return _isFavoriteMap[nombre_] ?: false
    }

    fun startConversation(user1: String, user2: String) {
        repository.startConversation(user1, user2)
    }

}
