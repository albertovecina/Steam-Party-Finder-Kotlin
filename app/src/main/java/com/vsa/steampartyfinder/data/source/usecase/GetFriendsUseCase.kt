package com.vsa.steampartyfinder.data.source.usecase

import com.vsa.steampartyfinder.base.extensions.toJson
import com.vsa.steampartyfinder.data.model.domain.PlayerSummary
import com.vsa.steampartyfinder.data.source.repository.SteamRepository
import io.reactivex.Observable

/**
 * Created by Alberto Vecina Sánchez on 4/12/17.
 */
class GetFriendsUseCase {

    private fun getSteamId(nickName: String): Observable<String> {
        return SteamRepository.getSteamId(nickName)
    }

    private fun getFriendsSteamIds(steamId: String): Observable<List<String>> {
        return SteamRepository.getFriendsSteamIds(steamId)
    }

    private fun getPlayerSummaries(playerIds: List<String>): Observable<List<PlayerSummary>> {
        val steamIds = playerIds.toString().replace("]", "")
        return SteamRepository.getPlayerSummaries(steamIds)
    }

    fun getSteamFriendSummariesByNickName(nickName: String): Observable<String> {
        return getSteamId(nickName)
                .flatMap { steamId -> getFriendsSteamIds(steamId) }
                .flatMap { friendIds ->
                    getPlayerSummaries(friendIds).map { response -> response.toJson() }
                }
    }
}