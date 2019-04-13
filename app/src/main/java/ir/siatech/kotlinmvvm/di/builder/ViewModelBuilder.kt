package ir.siatech.kotlinmvvm.di.builder

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ir.siatech.kotlinmvvm.ui.base.ViewModelFactory

@Module(includes = [ViewModelModule::class])
abstract class ViewModelBuilder {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory)
            : ViewModelProvider.Factory
}