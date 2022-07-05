package com.cj3dreams.justnotefy.di

import android.app.Application
import androidx.room.Room
import com.cj3dreams.justnotefy.BuildConfig
import com.cj3dreams.justnotefy.source.local.AppDb
import com.cj3dreams.justnotefy.source.remote.RestApiRequests
import com.cj3dreams.justnotefy.vm.NotesViewModel
import com.cj3dreams.justnotefy.vm.RoomViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://parseapi.back4app.com"

val networkModule = module {

    fun <Api> provideRemoteDataSource(api: Class<Api>) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also { client ->
                client.addInterceptor((Interceptor { chain ->
                    val newRequest = Request.Builder()
                        .addHeader("X-Parse-Application-Id", "ZHG1r0aOaeYrOcC5J8FhFAXix5R0aGQ2KDq9fTvb")
                        .addHeader("X-Parse-REST-API-Key", "3fTKahfcObJIHtVGeslry90jtGM7yqKMK50SXA2q")
                        .addHeader("Content-Type", "application/json")
                        .url(chain.request().url.toString())
                        .build()
                    chain.proceed(newRequest)
                }))
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addNetworkInterceptor(logging)
                }
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)

    factory { provideRemoteDataSource(RestApiRequests::class.java) }

    viewModel {
        NotesViewModel(get())
    }
}
val userDb = module {

    fun provideDataBase(application: Application) =
        Room.databaseBuilder(application, AppDb::class.java, "AppDb")
            .fallbackToDestructiveMigration()
            .build()

    fun provideDao(database: AppDb) = database.noteDao()

    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
}
val viewModel = module {
    viewModel {
        RoomViewModel(get())
    }
}