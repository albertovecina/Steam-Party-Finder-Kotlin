package com.vsa.steampartyfinder.ui.friends

import com.vsa.steampartyfinder.ui.adapter.players.PlayersDataProvider
import com.vsa.steampartyfinder.ui.base.BaseView
import java.io.Serializable

/**
 * Created by Alberto Vecina Sánchez on 6/12/17.
 */
interface FriendsView : BaseView {

    fun showFindButton()

    fun hideFindButton()

    fun setFriendsList(dataProvider: PlayersDataProvider)

    fun refreshFriendsList()

    fun navigateToGames(gamesList: Serializable)

}