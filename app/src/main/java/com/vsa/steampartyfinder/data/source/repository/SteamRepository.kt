package com.vsa.steampartyfinder.data.source.repository

import com.vsa.steampartyfinder.R
import com.vsa.steampartyfinder.SpfApplication
import com.vsa.steampartyfinder.data.model.domain.Game
import com.vsa.steampartyfinder.data.model.domain.Player
import com.vsa.steampartyfinder.data.source.ws.SteamClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alberto Vecina Sánchez on 5/12/17.
 */
object SteamRepository {

    fun getSteamId(nickName: String): Observable<String> {
        return SteamClient.create().observeSteamId(SpfApplication.context.resources.getString(R.string.steam_api_key), nickName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { response ->
                    if (response.response.success == "42")
                        throw Throwable("Cannot find user id")
                    else
                        response.response.steamid
                }
    }

    fun getFriendsSteamIds(steamId: String): Observable<List<String>> {
        return SteamClient.create().observeFriends(SpfApplication.context.resources.getString(R.string.steam_api_key), steamId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { response -> response.friendslist.friends.map { it -> it.steamId } }
    }

    fun getPlayers(steamIds: String): Observable<List<Player>> {
        return SteamClient.create().observePlayerSummaries(SpfApplication.context.resources.getString(R.string.steam_api_key), steamIds)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { response ->
                    response.response.players
                            .map { Player(it.steamid, it.personaname, it.avatarmedium) }
                }
    }

    fun getOwnedGames(steamId: String): Observable<List<Game>> {
        return SteamClient.create().observeOwnedGames(SpfApplication.context.resources.getString(R.string.steam_api_key),
                steamId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { response -> response.response.games.map { Game(it.appid, it.name, it.img_logo_url) } }
    }


}