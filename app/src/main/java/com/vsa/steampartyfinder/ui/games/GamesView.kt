package com.vsa.steampartyfinder.ui.games

import com.vsa.steampartyfinder.ui.adapter.games.GamesDataProvider

/**
 * Created by Alberto Vecina Sánchez on 14/12/17.
 */
interface GamesView {

    fun setGamesList(dataProvider: GamesDataProvider)

}