package com.vsa.steampartyfinder.feature.friends.presenter

import com.vsa.steampartyfinder.data.model.domain.Game
import com.vsa.steampartyfinder.data.model.domain.Player
import com.vsa.steampartyfinder.data.source.usecase.GetGamesUseCase
import com.vsa.steampartyfinder.common.view.adapter.players.PlayersDataProvider
import com.vsa.steampartyfinder.feature.friends.view.FriendsView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.io.Serializable
import javax.inject.Inject

/**
 * Created by Alberto Vecina Sánchez on 6/12/17.
 */
class FriendsPresenterImpl @Inject constructor(private val view: FriendsView,
                                               private val getGamesUseCase: GetGamesUseCase) : FriendsPresenter, PlayersDataProvider {

    private var friendsList: List<Player>? = null
    private val selectedFriendsList: MutableList<String> = ArrayList()

    override fun onCreate(steamId: String, friendsList: Serializable?) {
        selectedFriendsList.add(steamId)
        this.friendsList = friendsList as? ArrayList<Player>
        this.view.setFriendsList(this)
    }

    override fun onFindButtonClick() {
        requestGames(selectedFriendsList)
    }

    private fun requestGames(friendsList: List<String>) {
        getGamesUseCase.observeOwnedGames(friendsList)
                .subscribe(object : Observer<List<Game>> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                        this@FriendsPresenterImpl.view.showProgress()
                    }

                    override fun onNext(t: List<Game>) {
                        this@FriendsPresenterImpl.view.hideProgress()
                        if (t.isEmpty())
                            this@FriendsPresenterImpl.view.showInfoMessage("No common games found")
                        else
                            this@FriendsPresenterImpl.view.navigateToGames(ArrayList(t))
                    }

                    override fun onError(e: Throwable) {
                        this@FriendsPresenterImpl.view.hideProgress()
                        this@FriendsPresenterImpl.view.showErrorMessage(e.message.toString())
                    }

                })
    }

    override fun getFriendName(index: Int): String {
        return friendsList?.get(index)?.name ?: ""
    }

    override fun getFriendPortraitUrl(index: Int): String {
        return friendsList?.get(index)?.portraitUrl ?: ""
    }

    override fun friendsListSize(): Int {
        return friendsList?.size ?: 0
    }

    override fun isFriendSelected(position: Int): Boolean {
        return selectedFriendsList.contains(friendsList?.get(position)?.steamId)
    }

    override fun onFriendClick(position: Int) {
        if (isFriendSelected(position))
            selectedFriendsList.remove(friendsList?.get(position)?.steamId)
        else
            selectedFriendsList.add(friendsList?.get(position)?.steamId!!)
        this.view.refreshFriendsList()
        if (selectedFriendsList.size < 2)
            this.view.hideFindButton()
        else
            this.view.showFindButton()
    }

}