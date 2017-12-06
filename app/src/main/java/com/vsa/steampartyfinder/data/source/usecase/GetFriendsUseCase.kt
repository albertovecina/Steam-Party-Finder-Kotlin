package com.vsa.steampartyfinder.data.source.usecase

import com.vsa.steampartyfinder.data.model.domain.Player
import com.vsa.steampartyfinder.data.source.repository.SteamRepository
import io.reactivex.Observable

/**
 * Created by Alberto Vecina Sánchez on 4/12/17.
 */
object GetFriendsUseCase {

    private fun observeSteamId(nickName: String): Observable<String> {
        return SteamRepository.getSteamId(nickName)
    }

    private fun observeFriendsSteamIds(steamId: String): Observable<List<String>> {
        return SteamRepository.getFriendsSteamIds(steamId)
    }

    private fun observePlayerSummaries(playerIds: List<String>): Observable<List<Player>> {
        val steamIds = playerIds.toString().replace("]", "")
        return SteamRepository.getPlayers(steamIds)
    }

    fun observeSteamFriendsByNickName(nickName: String): Observable<List<Player>> {
        return observeSteamId(nickName)
                .flatMap { steamId -> observeFriendsSteamIds(steamId) }
                .flatMap { friendIds -> observePlayerSummaries(friendIds) }
    }
}