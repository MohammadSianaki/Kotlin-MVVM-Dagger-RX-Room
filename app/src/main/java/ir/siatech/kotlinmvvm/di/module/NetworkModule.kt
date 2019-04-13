package ir.siatech.kotlinmvvm.di.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import ir.siatech.kotlinmvvm.BuildConfig
import ir.siatech.kotlinmvvm.data.network.WebService
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val READ_TIME_OUT = 10000L
const val WRITE_TIME_OUT = 10000L
const val CONNECT_TIME_OUT = 10000L
const val CACHE_SIZE = 20 * 1024 * 1024L //20 MiB
const val BASE_URL = "https://newsapi.org/v2/"

@Module(includes = [ContextModule::class])
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor, httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache)
            : OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) builder.addInterceptor(httpLoggingInterceptor)
        builder
            .cache(cache)
            .addInterceptor(interceptor)
            .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.MILLISECONDS)
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideHttpInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("X-Api-Key", "a1ddb1d0adbd4061b5cd7fea3fcae531")
                .build()
            return@Interceptor it.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideCache(context: Context): Cache {
        return Cache(context.cacheDir, CACHE_SIZE)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()


    @Provides
    @Singleton
    fun provideWebService(retrofit: Retrofit): WebService {
        return retrofit.create(WebService::class.java)
    }
}