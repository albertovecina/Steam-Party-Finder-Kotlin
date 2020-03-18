package com.vsa.steampartyfinder.common.view.adapter.players

/**
 * Created by Alberto Vecina Sánchez on 6/12/17.
 */
interface PlayersDataProvider {

    fun getFriendName(index: Int): String

    fun getFriendPortraitUrl(index: Int): String

    fun friendsListSize(): Int

    fun isFriendSelected(position: Int): Boolean

    fun onFriendClick(position: Int)


}