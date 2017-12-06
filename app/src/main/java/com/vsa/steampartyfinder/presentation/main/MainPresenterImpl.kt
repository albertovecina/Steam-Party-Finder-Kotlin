package com.vsa.steampartyfinder.presentation.main

import android.util.Log
import com.vsa.steampartyfinder.base.extensions.toJson
import com.vsa.steampartyfinder.data.model.domain.Player
import com.vsa.steampartyfinder.data.source.usecase.GetFriendsUseCase
import com.vsa.steampartyfinder.ui.main.MainView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


/**
 * Created by Alberto Vecina Sánchez on 24/11/17.
 */
class MainPresenterImpl(view: MainView) : MainPresenter {

    val mView: MainView = view

    override fun onCreate() {
    }

    override fun onSearchFriendsButtonClick() {
        requestFriends()
    }

    private fun requestFriends() {
        GetFriendsUseCase.observeSteamFriendsByNickName(mView.getNickName())
                .subscribe(object : Observer<List<Player>> {
                    override fun onNext(t: List<Player>) {
                        Log.d("test", t.toJson())
                    }

                    override fun onSubscribe(d: Disposable) {
                        mView.showProgress()
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                        mView.hideProgress()
                    }
                })
    }

}