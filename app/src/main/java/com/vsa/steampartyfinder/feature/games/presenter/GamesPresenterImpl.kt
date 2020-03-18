package com.vsa.steampartyfinder.feature.games.presenter

import android.util.Log
import com.vsa.steampartyfinder.data.model.domain.Game
import com.vsa.steampartyfinder.data.model.domain.GameDetails
import com.vsa.steampartyfinder.data.source.usecase.GetGamesDetailsUseCase
import com.vsa.steampartyfinder.common.view.adapter.games.GamesDataProvider
import com.vsa.steampartyfinder.feature.games.view.GamesView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.io.Serializable
import javax.inject.Inject

/**
 * Created by Alberto Vecina Sánchez on 14/12/17.
 */
class GamesPresenterImpl @Inject constructor(private val view: GamesView,
                                             private val getGamesDetailUseCase: GetGamesDetailsUseCase) : GamesPresenter, GamesDataProvider {

    private lateinit var mGamesList: List<Game>
    private lateinit var mFilteredGamesList: MutableList<Game>
    private var mFilteredGameModes: ArrayList<GameDetails.GameMode> = ArrayList()

    override fun onCreate(gamesList: Serializable) {
        mGamesList = gamesList as List<Game>
        mFilteredGamesList = ArrayList(mGamesList)
        view.setGamesList(this)
        requestGameModes(mGamesList.filter { it.gameModes.isEmpty() })
    }

    private fun requestGameModes(games: List<Game>) {
        getGamesDetailUseCase.observeGameModes(games)
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
                        view.refreshList(gameIndex)

                        if (mFilteredGameModes.isNotEmpty()) {
                            if (game?.gameModes?.containsAll(mFilteredGameModes) == false) {
                                mFilteredGamesList.remove(game)
                                view.removeItemAt(gameIndex)
                            }

                        }

                    }
                })
    }

    override fun onFiltersChange(status: Array<Boolean>) {
        mFilteredGameModes = ArrayList(GameDetails.GameMode.values().filterIndexed { index, _ -> status[index] })
        mFilteredGamesList = ArrayList(mGamesList)
        mFilteredGamesList.removeAll { game -> game.gameModes.isNotEmpty() && !game.gameModes.containsAll(mFilteredGameModes) }
        view.refreshList()
    }

    private fun getIconByGameMode(gameMode: GameDetails.GameMode): Int {
        return when (gameMode) {
            GameDetails.GameMode.SINGLE -> view.getSinglePlayerIcon()
            GameDetails.GameMode.MULTIPLAYER -> view.getMultiPlayerIcon()
            GameDetails.GameMode.COOP -> view.getCoopIcon()
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