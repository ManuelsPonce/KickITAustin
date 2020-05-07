import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.example.kickitaustin.GamePost
import com.example.kickitaustin.TextChats
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {
    private var data = MutableLiveData<List<GamePost>>()
    private var textChats = MutableLiveData<List<String>>()
//    fun updateData(gamePost: GamePost) {
//        // Please add one line to this function (where indicated), leaving the rest
//        // of the code unchanged.
//        // All work done in viewModelScope will be
//        // canceled if viewModel is cleared.
//        // Dispatcher.Main - main thread
//        // Dispatcher.Default - background thread possibly on other processor
//        // Dispatcher.IO - for network or file system
//        // NB: We are working on a background thread.  That means we can't
//        // assign a livedata's value property directly.
//        // Read the LiveData documentation about what to do on a background thread.
//        // https://developer.android.com/reference/androidx/lifecycle/LiveData
//        viewModelScope.launch(
//            context = viewModelScope.coroutineContext
//                    + Dispatchers.Default){
//            // Please leave this delay
//            delay(2000)
//            // XXX Write me (one liner)
//            data.postValue()
//        }
//    }
    fun observeData(): LiveData<List<GamePost>> {
        // XXX Write me (one liner)
        return data
    }

    fun observeTexts(): LiveData<List<String>> {
        // XXX Write me (one liner)
        return textChats
    }
}