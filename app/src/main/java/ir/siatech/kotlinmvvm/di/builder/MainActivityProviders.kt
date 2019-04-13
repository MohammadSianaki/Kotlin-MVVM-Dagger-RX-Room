package ir.siatech.kotlinmvvm.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ir.siatech.kotlinmvvm.ui.news.NewsFragment


@Module
abstract class MainActivityProviders {

    @ContributesAndroidInjector
    abstract fun provideNewsFragment(): NewsFragment

}