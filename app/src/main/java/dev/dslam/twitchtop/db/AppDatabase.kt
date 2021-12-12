package dev.dslam.twitchtop.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.dslam.twitchtop.models.TopGame

@Database(entities = [TopGame::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getTopGameDao(): TopGameDao

    companion object {
        private var dbInstance: AppDatabase? = null
        fun getAppDB(context: Context): AppDatabase {
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "twitch.db"
                )
                .allowMainThreadQueries()
                .build()
            }

            return dbInstance!!
        }
    }
}