package ir.siatech.kotlinmvvm.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ir.siatech.kotlinmvvm.data.db.AppDatabase
import ir.siatech.kotlinmvvm.data.db.NewsDao
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideUserDao(appDataBase: AppDatabase): NewsDao {
        return appDataBase.newsDao()
    }
}