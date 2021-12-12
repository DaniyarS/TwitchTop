package dev.dslam.twitchtop.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.dslam.twitchtop.models.TopGame

@Dao
interface TopGameDao {
    @Query("SELECT * FROM top_game")
    fun getTopGameList() : List<TopGame>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopGameList(topGameList: List<TopGame>)
}
