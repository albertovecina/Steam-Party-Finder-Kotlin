package com.vsa.steampartyfinder.feature.games.filter.presenter

import java.io.Serializable

/**
 * Created by Alberto Vecina Sánchez on 15/12/17.
 */
interface GamesFilterPresenter {

    fun onViewCreated(gameModes: Serializable?)

    fun onAcceptButtonClick()

    fun onCancelButtonClick()

}