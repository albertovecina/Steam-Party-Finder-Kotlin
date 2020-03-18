package com.vsa.steampartyfinder.feature.main.view

import android.os.Bundle
import com.vsa.steampartyfinder.R
import com.vsa.steampartyfinder.feature.main.presenter.MainPresenter
import com.vsa.steampartyfinder.common.base.BaseActivity
import com.vsa.steampartyfinder.feature.friends.view.FriendsActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        presenter.onCreate()
    }

    private fun initViews() {
        buttonSearchFriends.setOnClickListener { presenter.onSearchFriendsButtonClick() }
    }

    override fun getNickName(): String {
        return editTextNickName.text.toString().trim()
    }

    override fun navigateToFriendsList(steamId: String, friendsList: Serializable) {
        FriendsActivity.open(this, steamId, friendsList)
    }

}
