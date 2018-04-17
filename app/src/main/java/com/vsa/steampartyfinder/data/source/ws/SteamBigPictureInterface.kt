package com.vsa.steampartyfinder.data.source.ws

import com.vsa.steampartyfinder.data.model.response.bigpicture.ResponseAppDetails
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Alberto Vecina Sánchez on 16/4/18.
 */
interface SteamBigPictureInterface {

    @GET("appdetails")
    fun observeGameDetails(@Query("appids") appIds: String): Observable<ResponseAppDetails>

}