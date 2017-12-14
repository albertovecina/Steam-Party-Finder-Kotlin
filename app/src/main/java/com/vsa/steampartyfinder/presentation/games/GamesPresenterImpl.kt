package com.vsa.steampartyfinder.presentation.games

import com.vsa.steampartyfinder.data.model.domain.Game
import com.vsa.steampartyfinder.ui.adapter.games.GamesDataProvider
import com.vsa.steampartyfinder.ui.games.GamesView
import java.io.Serializable

/**
 * Created by Alberto Vecina Sánchez on 14/12/17.
 */
class GamesPresenterImpl(view: GamesView) : GamesPresenter, GamesDataProvider {

    lateinit var mGamesList: List<Game>

    val mView: GamesView = view

    override fun onCreate(gamesList: Serializable) {
        mGamesList = gamesList as List<Game>
        mView.setGamesList(this)
    }

    override fun getName(position: Int): String {
        return mGamesList[position].name
    }

    override fun getImageUrl(position: Int): String {
        return mGamesList[position].imageUrl
    }

    override fun getGamesListSize(): Int {
        return mGamesList.size
    }

}