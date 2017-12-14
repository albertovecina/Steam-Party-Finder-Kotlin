package com.vsa.steampartyfinder.presentation.friends

import android.util.Log
import com.vsa.steampartyfinder.base.extensions.toJson
import com.vsa.steampartyfinder.data.model.domain.Game
import com.vsa.steampartyfinder.data.model.domain.Player
import com.vsa.steampartyfinder.data.source.usecase.GetGamesUseCase
import com.vsa.steampartyfinder.ui.adapter.PlayersDataProvider
import com.vsa.steampartyfinder.ui.friends.FriendsView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
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

    override fun onFindButtonClick() {
        GetGamesUseCase().observeOwnedGames(mSelectedFriendsList)
                .subscribe(object : Observer<List<Game>> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: List<Game>) {
                        Log.d("prueba", t.toJson())
                    }

                    override fun onError(e: Throwable) {
                        Log.d("prueba", e.message)
                    }

                })

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