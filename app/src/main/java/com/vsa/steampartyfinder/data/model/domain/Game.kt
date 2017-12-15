package com.vsa.steampartyfinder.data.model.domain

import java.io.Serializable

/**
 * Created by Alberto Vecina Sánchez on 14/12/17.
 */
data class Game(val appId: String, val name: String, val imageHash: String, var gameModes: ArrayList<GameDetails.GameMode> = ArrayList()) : Serializable {

    private val urlFormat: String = "http://media.steampowered.com/steamcommunity/public/images/apps/%s/%s.jpg"
    val imageUrl: String

    init {
        imageUrl = urlFormat.format(appId, imageHash)
    }

}