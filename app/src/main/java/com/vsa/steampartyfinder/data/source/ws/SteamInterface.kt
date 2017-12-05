package com.vsa.steampartyfinder.data.source.ws

import com.vsa.steampartyfinder.data.model.response.ResponseGetFriendsList
import com.vsa.steampartyfinder.data.model.response.ResponseGetPlayerSummaries
import com.vsa.steampartyfinder.data.model.response.ResponseResolveVanityURL
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Alberto Vecina Sánchez on 24/11/17.
 */
interface SteamInterface {

    @GET("/ISteamUser/ResolveVanityURL/v0001/")
    fun observeSteamId(@Query("key") apiKey: String, @Query("vanityurl") nickName: String): Observable<ResponseResolveVanityURL>

    @GET("/ISteamUser/GetFriendList/v0001/?relationship=friend")
    fun observeFriends(@Query("key") apiKey: String, @Query("steamid") steamId: String): Observable<ResponseGetFriendsList>

    @GET("/ISteamUser/GetPlayerSummaries/v0002/")
    fun observePlayerSummaries(@Query("key") apiKey: String, @Query("steamids") steamIds: String): Observable<ResponseGetPlayerSummaries>

}