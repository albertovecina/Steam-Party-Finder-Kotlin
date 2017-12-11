package com.vsa.steampartyfinder.presentation.friends

import com.vsa.steampartyfinder.data.model.domain.Player
import com.vsa.steampartyfinder.ui.adapter.PlayersDataProvider
import com.vsa.steampartyfinder.ui.friends.FriendsView
import java.io.Serializable

/**
 * Created by Alberto Vecina Sánchez on 6/12/17.
 */
class FriendsPresenterImpl(view: FriendsView) : FriendsPresenter, PlayersDataProvider {


    private val mView: FriendsView = view
    private var mFriendsList: List<Player>? = null
    private val mSelectedFriendsList: MutableList<String> = ArrayList()

    override fun onCreate(friendsList: Serializable) {
        mFriendsList = friendsList as? ArrayList<Player>
        mView.setFriendsList(this)
    }

    override fun getFriendName(index: Int): String {
        return mFriendsList?.get(index)?.name ?: ""
    }

    override fun getFriendPortraitUrl(index: Int): String {
        return mFriendsList?.get(index)?.portraitUrl ?: ""
    }

    override fun friendsListSize(): Int {
        return mFriendsList?.size ?: 0
    }

    override fun isFriendSelected(position: Int): Boolean {
        return mSelectedFriendsList.contains(mFriendsList?.get(position)?.steamId)
    }

    override fun onFriendClick(position: Int) {
        if (isFriendSelected(position))
            mSelectedFriendsList.remove(mFriendsList?.get(position)?.steamId)
        else
            mSelectedFriendsList.add(mFriendsList?.get(position)?.steamId!!)
        mView.refreshFriendsList()
    }

}