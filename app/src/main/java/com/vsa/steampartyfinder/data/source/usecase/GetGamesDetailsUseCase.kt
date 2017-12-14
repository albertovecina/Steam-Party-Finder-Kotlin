package com.vsa.steampartyfinder.data.source.usecase

import com.vsa.steampartyfinder.data.model.domain.Game
import com.vsa.steampartyfinder.data.model.domain.GameDetails
import com.vsa.steampartyfinder.data.source.repository.SteamSpyRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alberto Vecina Sánchez on 14/12/17.
 */
object GetGamesDetailsUseCase {

    private fun observeGameDetails(appId: String): Observable<GameDetails> {
        return SteamSpyRepository.observeGameDetails(appId)
    }

    fun observeGameModes(games: List<Game>): Observable<GameDetails> {
        val gameDetailsObservableList: List<Observable<GameDetails>> = games.map { observeGameDetails(it.appId) }
        return Observable.merge(gameDetailsObservableList)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

}