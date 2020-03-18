package com.vsa.steampartyfinder.feature.games.filter.view

import java.io.Serializable

/**
 * Created by Alberto Vecina Sánchez on 15/12/17.
 */
interface GamesFilterView {

    fun setSinglePlayerChecked()

    fun setMultiplayerChecked()

    fun setCoopChecked()

    fun isSinglePlayerChecked(): Boolean

    fun isSingleMultiplayerChecked(): Boolean

    fun isSingleCoopChecked(): Boolean

    fun notifyFiltersSelected(gameModes: Serializable)

    fun dismiss()

}