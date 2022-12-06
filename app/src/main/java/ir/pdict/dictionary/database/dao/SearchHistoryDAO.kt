package ir.pdict.dictionary.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.pdict.dictionary.database.model.SearchHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDAO {

    @Query("SELECT * FROM search_history ORDER BY created_at DESC LIMIT 10")
    fun getLatestSearch(): Flow<List<SearchHistory>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(searchHistory: SearchHistory)

    @Query("DELETE FROM search_history")
    fun deleteAll()
}