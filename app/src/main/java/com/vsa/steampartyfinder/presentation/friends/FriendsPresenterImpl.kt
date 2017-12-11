package com.vsa.steampartyfinder.presentation.friends

import com.vsa.steampartyfinder.data.model.domain.Player
import com.vsa.steampartyfinder.ui.adapter.PlayersDataProvider
import com.vsa.steampartyfinder.ui.friends.FriendsView
import java.io.Serializable

/**
 * Created by Alberto Vecina Sánchez on 6/12/17.
 */
class FriendsPresenterImpl(view: FriendsView) : FriendsPresenter, PlayersDataProvider {

    val mView: FriendsView = view
    var mFriendsList: List<Player>? = null

    override fun onCreate(friendsList: Serializable) {
        mFriendsList = friendsList as? ArrayList<Player>
        mView.setFriendsList(this)
    }

    override fun size(): Int {
        return mFriendsList?.size ?: 0
    }

    override fun getFriendName(index: Int): String {
        return mFriendsList?.get(index)?.name ?: ""
    }

    override fun getFriendPortrailrUrl(index: Int): String {
        return mFriendsList?.get(index)?.portraitUrl ?: ""
    }
}