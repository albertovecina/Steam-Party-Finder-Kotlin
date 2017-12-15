package com.vsa.steampartyfinder.data.source.ws

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Created by Alberto Vecina Sánchez on 14/12/17.
 */
object SteamSpyClient {

    private var clientInterface: SteamSpyInterface? = null

    fun create(): SteamSpyInterface {

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
                .baseUrl(Environment.ENDPOINT_STEAM_SPY)
                .client(httpClient.build())
                .build()
        clientInterface = retrofit.create(SteamSpyInterface::class.java)
        return clientInterface!!
    }

}