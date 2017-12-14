package com.vsa.steampartyfinder.ui.adapter.games

/**
 * Created by Alberto Vecina Sánchez on 14/12/17.
 */
interface GamesDataProvider {

    fun getName(position: Int): String

    fun getImageUrl(position: Int): String

    fun getGamesListSize(): Int

}