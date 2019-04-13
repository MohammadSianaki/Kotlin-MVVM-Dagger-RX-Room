package ir.siatech.kotlinmvvm.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ir.siatech.kotlinmvvm.ui.MainActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityProviders::class])
    abstract fun bindMainActivity(): MainActivity
}