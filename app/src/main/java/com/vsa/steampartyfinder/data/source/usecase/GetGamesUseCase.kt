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
        return Observable.zip(observables, { res -> intersect(res) })

    }

    private fun intersect(response: Array<Any>): List<Game> {
        if (response.isNotEmpty()) {
            var res: Set<Game> = (response[0] as List<Game>).toSet()
            for (item in response)
                res = res.intersect(item as Iterable<Game>)
            return ArrayList(res)
        }
        return ArrayList()
    }

}