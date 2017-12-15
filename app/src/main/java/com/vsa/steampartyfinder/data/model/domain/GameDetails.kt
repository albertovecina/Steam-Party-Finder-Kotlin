package com.vsa.steampartyfinder.data.model.domain

/**
 * Created by Alberto Vecina Sánchez on 14/12/17.
 */

data class GameDetails(var appId: String = "", var gameModes: MutableList<GameMode> = ArrayList()) {

    enum class GameMode { SINGLE, COOP, MULTIPLAYER }

}