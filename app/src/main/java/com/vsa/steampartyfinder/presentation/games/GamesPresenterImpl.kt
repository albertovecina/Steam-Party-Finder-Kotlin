package com.vsa.steampartyfinder.presentation.games

import com.vsa.steampartyfinder.data.model.domain.Game
import com.vsa.steampartyfinder.data.model.domain.GameDetails
import com.vsa.steampartyfinder.data.source.usecase.GetGamesDetailsUseCase
import com.vsa.steampartyfinder.ui.adapter.games.GamesDataProvider
import com.vsa.steampartyfinder.ui.games.GamesView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.io.Serializable

/**
 * Created by Alberto Vecina Sánchez on 14/12/17.
 */
class GamesPresenterImpl(view: GamesView) : GamesPresenter, GamesDataProvider {

    private lateinit var mGamesList: List<Game>
    private var mGamesDetailsList: MutableMap<String, List<GameDetails.GameMode>> = HashMap()

    private val mView: GamesView = view

    override fun onCreate(gamesList: Serializable) {
        mGamesList = gamesList as List<Game>
        mView.setGamesList(this)
        requestGameModes(mGamesList)
    }

    private fun requestGameModes(games: List<Game>) {
        GetGamesDetailsUseCase.observeGameModes(games)
                .subscribe(object : Observer<GameDetails> {
                    override fun onComplete() {
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: GameDetails) {
                        mGamesDetailsList.put(t.appId, t.gameModes)
                        mView.refreshList(mGamesList.indexOfFirst { it.appId == t.appId })
                    }
                })
    }

    private fun getIconByGameMode(gameMode: GameDetails.GameMode): Int {
        return when (gameMode) {
            GameDetails.GameMode.SINGLE -> mView.getSinglePlayerIcon()
            GameDetails.GameMode.MULTIPLAYER -> mView.getMultiPlayerIcon()
            GameDetails.GameMode.COOP -> mView.getCoopIcon()
        }
    }

    override fun getName(position: Int): String {
        return mGamesList[position].name
    }

    override fun getImageUrl(position: Int): String {
        return mGamesList[position].imageUrl
    }

    override fun getGameModes(position: Int): List<Int>? {
        return mGamesDetailsList?.get(mGamesList[position].appId)?.map { getIconByGameMode(it) }
    }

    override fun getGamesListSize(): Int {
        return mGamesList.size
    }


}