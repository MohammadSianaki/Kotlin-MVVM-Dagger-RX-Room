package ir.siatech.kotlinmvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.siatech.kotlinmvvm.data.model.Article

@Database(entities = [Article::class], version = AppDatabase.VERSION)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "news.db"
        const val VERSION = 1

        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()

        @JvmStatic
        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                        .databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                        .build()
                }
                return INSTANCE!!
            }
        }
    }

    abstract fun newsDao(): NewsDao
}