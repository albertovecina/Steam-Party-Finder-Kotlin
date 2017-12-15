package com.vsa.steampartyfinder.ui.main

import java.io.Serializable

/**
 * Created by Alberto Vecina Sánchez on 24/11/17.
 */
interface MainView {

    fun showProgress()

    fun hideProgress()

    fun getNickName(): String

    fun navigateToFriendsList(steamId: String, friendsList: Serializable)

}