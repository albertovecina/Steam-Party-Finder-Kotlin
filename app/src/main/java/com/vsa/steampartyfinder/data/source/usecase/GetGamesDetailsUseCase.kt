package com.vsa.steampartyfinder.data.source.usecase

import com.vsa.steampartyfinder.data.model.domain.Game
import com.vsa.steampartyfinder.data.model.domain.GameDetails
import com.vsa.steampartyfinder.data.source.repository.SteamSpyRepository
import io.reactivex.Observable

/**
 * Created by Alberto Vecina Sánchez on 14/12/17.
 */
object GetGamesDetailsUseCase {

    private fun observeGameDetails(appId: String): Observable<GameDetails> {
        return SteamSpyRepository.observeGameDetails(appId)
    }

    fun observeGameDetails(games: List<Game>): Observable<List<GameDetails>> {
        val gameDetailsObservableList: List<Observable<GameDetails>> = games.map { observeGameDetails(it.appId) }
        return Observable.zip(gameDetailsObservableList, { res -> res.map { it as GameDetails } })
    }

}