package com.vsa.steampartyfinder.common.di.module

import com.vsa.steampartyfinder.data.source.ws.Environment
import com.vsa.steampartyfinder.data.source.ws.SteamApiInterface
import com.vsa.steampartyfinder.data.source.ws.SteamBigPictureApiInterface
import com.vsa.steampartyfinder.data.source.ws.SteamSpyApiInterface
import com.vsa.steampartyfinder.common.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
class NetworkModule {

    @PerApplication
    @Provides
    fun providesHttpClient(): OkHttpClient =
            OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            }.build()

    @PerApplication
    @Provides
    fun providesRetrofitBuilder(httpClient: OkHttpClient): Retrofit.Builder = Retrofit.Builder()
            .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(
                    GsonConverterFactory.create())
            .client(httpClient)

    @PerApplication
    @Provides
    fun providesSteamApiInterface(retrofitBuilder: Retrofit.Builder): SteamApiInterface =
            retrofitBuilder.baseUrl(Environment.ENDPOINT_STEAM)
                    .build()
                    .create(SteamApiInterface::class.java)

    @PerApplication
    @Provides
    fun providesSteamSpyApiInterface(retrofitBuilder: Retrofit.Builder): SteamSpyApiInterface =
            retrofitBuilder.baseUrl(Environment.ENDPOINT_STEAM_SPY)
                    .build()
                    .create(SteamSpyApiInterface::class.java)

    @PerApplication
    @Provides
    fun providesSteamBigPictureApiInterface(retrofitBuilder: Retrofit.Builder): SteamBigPictureApiInterface =
            retrofitBuilder.baseUrl(Environment.ENDPOINT_STEAM_BIG_PRICTURE)
                    .build()
                    .create(SteamBigPictureApiInterface::class.java)

}