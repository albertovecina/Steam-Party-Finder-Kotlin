package com.vsa.steampartyfinder.presentation.friends

import java.io.Serializable

/**
 * Created by Alberto Vecina Sánchez on 6/12/17.
 */
interface FriendsPresenter {

    fun onCreate(friendsList: Serializable)

    fun onFindButtonClick()

}