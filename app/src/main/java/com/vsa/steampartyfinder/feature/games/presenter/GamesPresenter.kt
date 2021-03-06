package com.vsa.steampartyfinder.feature.games.presenter

import java.io.Serializable

/**
 * Created by Alberto Vecina Sánchez on 14/12/17.
 */
interface GamesPresenter {

    fun onCreate(gamesList: Serializable)

    fun onFiltersChange(status:Array<Boolean>)

}