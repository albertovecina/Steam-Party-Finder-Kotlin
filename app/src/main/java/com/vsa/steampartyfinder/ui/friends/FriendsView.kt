package com.vsa.steampartyfinder.ui.friends

import com.vsa.steampartyfinder.ui.adapter.PlayersDataProvider

/**
 * Created by Alberto Vecina Sánchez on 6/12/17.
 */
interface FriendsView {

    fun setFriendsList(dataProvider: PlayersDataProvider)

    fun refreshFriendsList()

}