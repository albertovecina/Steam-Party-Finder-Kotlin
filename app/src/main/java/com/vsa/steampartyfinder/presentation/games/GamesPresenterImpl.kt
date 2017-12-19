package com.vsa.steampartyfinder.presentation.games

import android.util.Log
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
    private lateinit var mFilteredGamesList: MutableList<Game>
    private var mFilteredGameModes: ArrayList<GameDetails.GameMode> = ArrayList()

    private val mView: GamesView = view

    override fun onCreate(gamesList: Serializable) {
        mGamesList = gamesList as List<Game>
        mFilteredGamesList = ArrayList(mGamesList)
        mView.setGamesList(this)
        requestGameModes(mGamesList)
    }

    private fun requestGameModes(games: List<Game>) {
        GetGamesDetailsUseCase.observeGameModes(games)
                .subscribe(object : Observer<GameDetails> {
                    override fun onComplete() {
                        Log.d("prueba", "asd")
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(gameDetails: GameDetails) {
                        val game: Game? = mGamesList.find { it.appId == gameDetails.appId }
                        game?.gameModes = ArrayList(gameDetails.gameModes)

                        val gameIndex: Int = mFilteredGamesList.indexOf(game)
                        mView.refreshList(gameIndex)

                        if (mFilteredGameModes.isNotEmpty()) {
                            if (game?.gameModes?.containsAll(mFilteredGameModes) == false) {
                                mFilteredGamesList.remove(game)
                                mView.removeItemAt(gameIndex)
                            }

                        }

                    }
                })
    }

    override fun onFiltersChange(status: Array<Boolean>) {
        mFilteredGameModes = ArrayList(GameDetails.GameMode.values().filterIndexed { index, _ -> status[index] })
        mFilteredGamesList = ArrayList(mGamesList)
        mFilteredGamesList.removeAll { game -> game.gameModes.isNotEmpty() && !game.gameModes.containsAll(mFilteredGameModes) }
        mView.refreshList()
    }

    private fun getIconByGameMode(gameMode: GameDetails.GameMode): Int {
        return when (gameMode) {
            GameDetails.GameMode.SINGLE -> mView.getSinglePlayerIcon()
            GameDetails.GameMode.MULTIPLAYER -> mView.getMultiPlayerIcon()
            GameDetails.GameMode.COOP -> mView.getCoopIcon()
        }
    }

    override fun getName(position: Int): String {
        return mFilteredGamesList[position].name
    }

    override fun getImageUrl(position: Int): String {
        return mFilteredGamesList[position].imageUrl
    }

    override fun getGameModes(position: Int): List<Int>? {
        return mFilteredGamesList[position].gameModes.map { getIconByGameMode(it) }
    }

    override fun getGamesListSize(): Int {
        return mFilteredGamesList.size
    }


}