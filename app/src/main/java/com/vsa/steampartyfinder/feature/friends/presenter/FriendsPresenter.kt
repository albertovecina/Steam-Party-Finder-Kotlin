package com.vsa.steampartyfinder.feature.friends.presenter

import java.io.Serializable

/**
 * Created by Alberto Vecina Sánchez on 6/12/17.
 */
interface FriendsPresenter {

    fun onCreate(steamId: String, friendsList: Serializable?)

    fun onFindButtonClick()

}