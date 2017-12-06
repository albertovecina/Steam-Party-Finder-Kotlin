package com.vsa.steampartyfinder.ui.main

/**
 * Created by Alberto Vecina Sánchez on 24/11/17.
 */
interface MainView {

    fun showProgress()

    fun hideProgress()

    fun getNickName(): String

    fun navigateToFriendsList()

}