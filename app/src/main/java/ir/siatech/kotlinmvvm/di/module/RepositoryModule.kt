package ir.siatech.kotlinmvvm.di.module

import dagger.Module
import dagger.Provides
import ir.siatech.kotlinmvvm.data.NewsRepository
import ir.siatech.kotlinmvvm.data.NewsRepositoryImpl
import ir.siatech.kotlinmvvm.data.db.AppDatabase
import ir.siatech.kotlinmvvm.data.network.WebService
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class, NetworkModule::class])
class RepositoryModule {


    @Provides
    @Singleton
    fun provideNewsRepository(webService: WebService, database: AppDatabase): NewsRepository {
        return NewsRepositoryImpl(webService, database)
    }
}