package ir.siatech.kotlinmvvm.di.qualifier

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@MapKey
@MustBeDocumented
@Target(
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.CONSTRUCTOR
)
annotation class ViewModelKey(val value: KClass<out ViewModel>)