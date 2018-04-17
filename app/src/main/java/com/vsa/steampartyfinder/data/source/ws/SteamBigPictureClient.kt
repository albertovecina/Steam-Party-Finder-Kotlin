package com.vsa.steampartyfinder.data.source.ws

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Created by Alberto Vecina Sánchez on 16/4/18.
 */
object SteamBigPictureClient {

    private var clientInterface: SteamBigPictureInterface? = null

    fun create(): SteamBigPictureInterface {

        if (clientInterface != null)
            return clientInterface!!

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)  // <-- this is the important line!

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(
                        GsonConverterFactory.create())
                .baseUrl(Environment.ENDPOINT_STEAM_BIG_PRICTURE)
                .client(httpClient.build())
                .build()
        clientInterface = retrofit.create(SteamBigPictureInterface::class.java)
        return clientInterface!!
    }

}