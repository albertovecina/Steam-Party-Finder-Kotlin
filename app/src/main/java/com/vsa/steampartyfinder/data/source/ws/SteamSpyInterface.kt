package com.vsa.steampartyfinder.data.source.ws

import com.vsa.steampartyfinder.data.model.response.steamspy.ResponseAppDetails
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Alberto Vecina Sánchez on 14/12/17.
 */
interface SteamSpyInterface {

    @GET("/api.php?request=appdetails")
    fun observeGameDetails(@Query("appid") appId: String): Observable<ResponseAppDetails>

}