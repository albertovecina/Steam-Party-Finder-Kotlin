package com.vsa.steampartyfinder.data.source.usecase

import com.vsa.steampartyfinder.data.model.domain.Game
import com.vsa.steampartyfinder.data.source.repository.SteamRepository
import io.reactivex.Observable

/**
 * Created by Alberto Vecina Sánchez on 11/12/17.
 */
class GetGamesUseCase {

    private fun observeOwnedGames(steamId: String): Observable<List<Game>> {
        return SteamRepository.getOwnedGames(steamId)
    }

    fun observeOwnedGames(steamIds: List<String>): Observable<List<Game>> {
        val observables: List<Observable<List<Game>>> = steamIds.map { steamId -> observeOwnedGames(steamId) }
        return Observable.zip(observables, { res -> intersect(res).sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER, { it.name })) })
    }

    private fun intersect(response: Array<Any>): List<Game> {
        if (response.isNotEmpty()) {
            with(response.filterIsInstance(List::class.java)) {
                with(map { it -> it.filterIsInstance(Game::class.java) }) {
                    var res: Set<Game> = get(0).toSet()
                    forEach { res = it.intersect(res) }
                    return ArrayList(res)
                }
            }

        }
        return ArrayList()
    }

}