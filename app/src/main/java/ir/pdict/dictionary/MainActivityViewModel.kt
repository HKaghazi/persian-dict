package ir.pdict.dictionary

import androidx.lifecycle.*
import ir.pdict.dictionary.database.model.SearchHistory
import ir.pdict.dictionary.database.repository.SearchHistoryRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: SearchHistoryRepository): ViewModel() {

    val latestSearch: LiveData<List<SearchHistory>> = repository.getLatestSearch.asLiveData()

    fun insert(searchHistory: SearchHistory) = viewModelScope.launch {
        repository.insert(searchHistory)
    }
}

class MainActivityViewModelFactory(private val repository: SearchHistoryRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}