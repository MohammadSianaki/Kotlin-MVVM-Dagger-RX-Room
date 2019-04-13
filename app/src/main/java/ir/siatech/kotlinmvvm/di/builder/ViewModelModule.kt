package ir.siatech.kotlinmvvm.di.builder

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ir.siatech.kotlinmvvm.di.qualifier.ViewModelKey
import ir.siatech.kotlinmvvm.ui.HomeViewModel
import ir.siatech.kotlinmvvm.ui.news.NewsViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(newsViewModel: NewsViewModel): ViewModel
}