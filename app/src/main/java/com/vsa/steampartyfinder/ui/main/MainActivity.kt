package com.vsa.steampartyfinder.ui.main

import android.os.Bundle
import com.vsa.steampartyfinder.R
import com.vsa.steampartyfinder.presentation.main.MainPresenter
import com.vsa.steampartyfinder.presentation.main.MainPresenterImpl
import com.vsa.steampartyfinder.ui.base.BaseActivity
import com.vsa.steampartyfinder.ui.friends.FriendsActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : BaseActivity(), MainView {

    private val mPresenter: MainPresenter = MainPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        mPresenter.onCreate()
    }

    private fun initViews() {
        buttonSearchFriends.setOnClickListener { mPresenter.onSearchFriendsButtonClick() }
    }

    override fun getNickName(): String {
        return editTextNickName.text.toString()
    }

    override fun navigateToFriendsList(steamId: String, friendsList: Serializable) {
        FriendsActivity.open(this, steamId, friendsList)
    }

}
