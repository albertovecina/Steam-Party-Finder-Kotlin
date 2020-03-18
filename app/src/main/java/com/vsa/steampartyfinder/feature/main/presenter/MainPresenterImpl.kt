package com.vsa.steampartyfinder.feature.main.presenter

import com.vsa.steampartyfinder.data.model.domain.Player
import com.vsa.steampartyfinder.data.source.usecase.GetFriendsUseCase
import com.vsa.steampartyfinder.feature.main.view.MainView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject


/**
 * Created by Alberto Vecina Sánchez on 24/11/17.
 */

class MainPresenterImpl @Inject constructor(private val view: MainView,
                                            private val getFriendsUseCase: GetFriendsUseCase) : MainPresenter {


    lateinit var steamId: String

    override fun onCreate() {
    }

    override fun onSearchFriendsButtonClick() {
        requestFriends()
    }

    private fun requestFriends() {
        getFriendsUseCase.observeSteamId(view.getNickName())
                .flatMap { t ->
                    steamId = t
                    getFriendsUseCase.observeSteamFriendsBySteamId(steamId)
                }.subscribe(object : Observer<List<Player>> {
                    override fun onNext(t: List<Player>) {
                        view.navigateToFriendsList(steamId, ArrayList(t))
                    }

                    override fun onSubscribe(d: Disposable) {
                        view.showProgress()
                    }

                    override fun onError(e: Throwable) {
                        view.hideProgress()
                        view.showErrorMessage(e.message.toString())
                    }

                    override fun onComplete() {
                        view.hideProgress()
                    }
                })
    }

}