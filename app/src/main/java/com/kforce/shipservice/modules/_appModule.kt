package com.kforce.shipservice.modules

import com.kforce.shipservice.helpers.BASE_URL
import com.kforce.shipservice.repository.ShipRepository
import com.kforce.shipservice.repository.api.ShipService
import com.kforce.shipservice.viewmodels.MainScreenViewModel
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/** Define module variables */
val networkModule = module {
    single { provideRetrofit(get(), get())}
    single { provideOkHttpClient() }
    single { provideMoshi() }
}

val repositoryModule = module {
    single { ShipRepository(get()) }
    single { provideAPI(get()) }
}

val viewModelModule = module {
    viewModel { MainScreenViewModel(get()) }
}


/**
 * @return [Retrofit] instance
 */
private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    moshi: Moshi
): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(okHttpClient)
    .build()

/**
 * @return [OkHttpClient] instance
 */

private fun provideOkHttpClient() = OkHttpClient.Builder()
    .readTimeout(10L, TimeUnit.SECONDS)
    .readTimeout(15L, TimeUnit.SECONDS)
    .addInterceptor(
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    )
    .build()

/**
 * @return [Moshi] instance
 */
private fun provideMoshi(): Moshi = Moshi.Builder().build()

/**
 * @return [ShipService] instance
 */
private fun provideAPI(retrofit: Retrofit): ShipService = retrofit.create(ShipService::class.java)