package ir.siatech.kotlinmvvm.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ir.siatech.kotlinmvvm.core.MyApp
import ir.siatech.kotlinmvvm.di.builder.ActivityBuilder
import ir.siatech.kotlinmvvm.di.module.ContextModule
import ir.siatech.kotlinmvvm.di.module.DatabaseModule
import ir.siatech.kotlinmvvm.di.module.NetworkModule
import ir.siatech.kotlinmvvm.di.module.RepositoryModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        RepositoryModule::class,
        DatabaseModule::class,
        ActivityBuilder::class,
        ContextModule::class,
        NetworkModule::class]
)
interface ApplicationComponent : AndroidInjector<MyApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}