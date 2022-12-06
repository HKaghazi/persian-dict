package ir.pdict.dictionary

import android.app.Application
import ir.pdict.dictionary.database.DictionaryDatabase
import ir.pdict.dictionary.database.repository.SearchHistoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class DictionaryApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { DictionaryDatabase.getDatabase(this, applicationScope) }
    val searchHistoryRepository by lazy { SearchHistoryRepository(database.searchHistoryDao()) }

}