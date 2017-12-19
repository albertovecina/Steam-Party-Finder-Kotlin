package com.vsa.steampartyfinder.ui.games

import com.vsa.steampartyfinder.ui.adapter.games.GamesDataProvider
import java.io.Serializable

/**
 * Created by Alberto Vecina Sánchez on 14/12/17.
 */
interface GamesView {

    fun setGamesList(dataProvider: GamesDataProvider)

    fun showFiltersDialog(gameModes: Serializable)

    fun getSinglePlayerIcon(): Int

    fun getMultiPlayerIcon(): Int

    fun getCoopIcon(): Int

    fun refreshList()

    fun refreshList(position: Int)

    fun removeItemAt(position: Int)

}