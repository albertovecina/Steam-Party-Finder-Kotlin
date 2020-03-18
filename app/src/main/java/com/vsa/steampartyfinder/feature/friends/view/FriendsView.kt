package com.vsa.steampartyfinder.feature.friends.view

import com.vsa.steampartyfinder.common.view.adapter.players.PlayersDataProvider
import com.vsa.steampartyfinder.common.base.BaseView
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