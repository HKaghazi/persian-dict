package ir.pdict.dictionary.database.repository

import androidx.annotation.WorkerThread
import ir.pdict.dictionary.database.dao.SearchHistoryDAO
import ir.pdict.dictionary.database.model.SearchHistory
import kotlinx.coroutines.flow.Flow

class SearchHistoryRepository(private val searchHistoryDoa: SearchHistoryDAO) {

    val getLatestSearch: Flow<List<SearchHistory>> = searchHistoryDoa.getLatestSearch()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(searchHistory: SearchHistory) {
        searchHistoryDoa.insert(searchHistory)
    }
}