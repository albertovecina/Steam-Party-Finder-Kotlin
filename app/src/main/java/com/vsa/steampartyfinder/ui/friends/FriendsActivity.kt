package com.vsa.steampartyfinder.ui.friends

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.vsa.steampartyfinder.R
import com.vsa.steampartyfinder.presentation.friends.FriendsPresenter
import com.vsa.steampartyfinder.presentation.friends.FriendsPresenterImpl
import com.vsa.steampartyfinder.ui.adapter.players.PlayersAdapter
import com.vsa.steampartyfinder.ui.adapter.players.PlayersDataProvider
import com.vsa.steampartyfinder.ui.base.BaseActivity
import com.vsa.steampartyfinder.ui.games.GamesActivity
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.activity_friends.*
import java.io.Serializable

class FriendsActivity : BaseActivity(), FriendsView {

    companion object {
        private val EXTRA_STEAM_ID = "extra_steam_id"
        private val EXTRA_FRIENDS_LIST = "extra_friends_list"

        fun open(context: Context, steamId: String, friendsList: Serializable) {
            val intent = Intent(context, FriendsActivity::class.java)
            intent.putExtra(EXTRA_STEAM_ID, steamId)
            intent.putExtra(EXTRA_FRIENDS_LIST, friendsList)
            context.startActivity(intent)
        }

    }

    private val mPresenter: FriendsPresenter = FriendsPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        initViews()
        mPresenter.onCreate(
                intent.extras.getString(EXTRA_STEAM_ID),
                intent.extras.getSerializable(EXTRA_FRIENDS_LIST))
    }

    private fun initViews() {
        recyclerViewFriends.layoutManager = LinearLayoutManager(this)
        recyclerViewFriends.addItemDecoration(HorizontalDividerItemDecoration.Builder(this)
                .build())
        buttonFindGames.setOnClickListener { mPresenter.onFindButtonClick() }
    }

    override fun setFriendsList(dataProvider: PlayersDataProvider) {
        recyclerViewFriends.adapter = PlayersAdapter(dataProvider)
    }

    override fun refreshFriendsList() {
        recyclerViewFriends.adapter?.notifyDataSetChanged()
    }

    override fun navigateToGames(gamesList: Serializable) {
        GamesActivity.open(this, gamesList)
    }

}
