package com.vsa.steampartyfinder.ui.main

import com.vsa.steampartyfinder.ui.base.BaseView
import java.io.Serializable

/**
 * Created by Alberto Vecina Sánchez on 24/11/17.
 */
interface MainView : BaseView {

    fun getNickName(): String

    fun navigateToFriendsList(steamId: String, friendsList: Serializable)

}