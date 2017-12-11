package com.vsa.steampartyfinder.ui.adapter

/**
 * Created by Alberto Vecina Sánchez on 6/12/17.
 */
interface PlayersDataProvider : ListDataProvider {

    fun getFriendName(index: Int): String

    fun getFriendPortrailrUrl(index: Int): String

}