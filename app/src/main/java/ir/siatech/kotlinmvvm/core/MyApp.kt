package ir.siatech.kotlinmvvm.core

import android.content.Context
import androidx.multidex.MultiDex
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import ir.siatech.kotlinmvvm.di.component.DaggerApplicationComponent

class MyApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}