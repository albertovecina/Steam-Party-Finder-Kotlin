package com.vsa.steampartyfinder.data.source.usecase

import com.vsa.steampartyfinder.data.model.domain.Player
import com.vsa.steampartyfinder.data.source.repository.SteamRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Alberto Vecina Sánchez on 4/12/17.
 */
class GetFriendsUseCase @Inject constructor(private val steamRepository: SteamRepository) {

    fun observeSteamId(nickName: String): Observable<String> {
        return steamRepository.getSteamId(nickName)
    }

    private fun observeFriendsSteamIds(steamId: String): Observable<List<String>> {
        return steamRepository.getFriendsSteamIds(steamId)
    }

    private fun observePlayerSummaries(playerIds: List<String>): Observable<List<Player>> {
        val steamIds = playerIds.toString().replace("]", "")
        return steamRepository.getPlayers(steamIds)
                .map { players -> players.sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER, { it.name })) }
    }

    fun observeSteamFriendsBySteamId(steamId: String): Observable<List<Player>> {
        return observeFriendsSteamIds(steamId)
                .flatMap { friendIds -> observePlayerSummaries(friendIds) }
    }

    fun observeSteamFriendsByNickName(nickName: String): Observable<List<Player>> {
        return observeSteamId(nickName)
                .flatMap { steamId -> observeFriendsSteamIds(steamId) }
                .flatMap { friendIds -> observePlayerSummaries(friendIds) }
                .map { it -> it.sortedBy { it.name } }
    }
}