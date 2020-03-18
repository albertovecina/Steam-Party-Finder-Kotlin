package com.vsa.steampartyfinder.data.source.ws

import com.vsa.steampartyfinder.data.model.response.steam.ResponseGetFriendsList
import com.vsa.steampartyfinder.data.model.response.steam.ResponseGetOwnedGames
import com.vsa.steampartyfinder.data.model.response.steam.ResponseGetPlayerSummaries
import com.vsa.steampartyfinder.data.model.response.steam.ResponseResolveVanityURL
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Alberto Vecina Sánchez on 24/11/17.
 */
interface SteamApiInterface {

    @GET("/ISteamUser/ResolveVanityURL/v0001/")
    fun observeSteamId(@Query("key") apiKey: String, @Query("vanityurl") nickName: String): Observable<ResponseResolveVanityURL>

    @GET("/ISteamUser/GetFriendList/v0001/?relationship=friend")
    fun observeFriends(@Query("key") apiKey: String, @Query("steamid") steamId: String): Observable<ResponseGetFriendsList>

    @GET("/ISteamUser/GetPlayerSummaries/v0002/")
    fun observePlayerSummaries(@Query("key") apiKey: String, @Query("steamids") steamIds: String): Observable<ResponseGetPlayerSummaries>

    @GET("/IPlayerService/GetOwnedGames/v0001/?include_appinfo=1")
    fun observeOwnedGames(@Query("key") apiKey: String, @Query("steamid") steamId: String): Observable<ResponseGetOwnedGames>

}