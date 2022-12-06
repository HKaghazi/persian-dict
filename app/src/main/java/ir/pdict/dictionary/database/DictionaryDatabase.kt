package ir.pdict.dictionary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ir.pdict.dictionary.database.dao.SearchHistoryDAO
import ir.pdict.dictionary.database.model.SearchHistory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(SearchHistory::class), version = 1, exportSchema = false)
public abstract class DictionaryDatabase : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDAO

    private class DictionaryDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database)
                }
            }
        }

        private fun populateDatabase(db: DictionaryDatabase) {
//            val seachHistoryDAO = db.searchHistoryDao()
//
//            seachHistoryDAO.deleteAll()
//            val sample = SearchHistory(1, "test word", 1234567)
//            seachHistoryDAO.insert(sample)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: DictionaryDatabase? = null

        @Synchronized
        fun getDatabase(ctx: Context, scope: CoroutineScope): DictionaryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    DictionaryDatabase::class.java,
                    "dictionary_database"
                ).addCallback(DictionaryDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
