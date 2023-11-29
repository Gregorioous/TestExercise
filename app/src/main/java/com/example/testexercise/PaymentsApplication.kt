package com.example.testexercise

import android.app.Application
import com.example.testexercise.api.AuthInterceptor
import com.example.testexercise.api.HeaderInterceptor
import com.example.testexercise.api.UserApi
import com.example.testexercise.repository.PaymentsRepository
import com.example.testexercise.repository.PaymentsRepositoryImpl
import com.example.testexercise.repository.RegistrationRepository
import com.example.testexercise.repository.RegistrationRepositoryImpl
import com.example.testexercise.viewmodel.PaymentsViewModel
import com.example.testexercise.viewmodel.RegistrationViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PaymentsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PaymentsApplication)
            androidLogger(level = Level.DEBUG)

            modules(
                mutableListOf(diModule)
            )
        }
    }

    private val diModule = module {
        single { provideRetrofit() }
        single { AuthInterceptor() }
        factory { provideNetworkApi(get()) }
        single<RegistrationRepository> { RegistrationRepositoryImpl(get()) }
        single<PaymentsRepository> { PaymentsRepositoryImpl(get()) }

        viewModel {
            RegistrationViewModel(get())
        }
        viewModel {
            PaymentsViewModel(get())
        }

    }


    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://easypay.world/api-test/").client(
            OkHttpClient().newBuilder()
                .addInterceptor(AuthInterceptor())
                .addInterceptor(HeaderInterceptor())
                .build()
        )
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun provideNetworkApi(retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)

}