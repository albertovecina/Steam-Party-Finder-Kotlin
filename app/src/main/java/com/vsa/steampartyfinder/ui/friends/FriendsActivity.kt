package com.vsa.steampartyfinder.ui.friends

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.vsa.steampartyfinder.R
import com.vsa.steampartyfinder.presentation.friends.FriendsPresenter
import com.vsa.steampartyfinder.presentation.friends.FriendsPresenterImpl
import com.vsa.steampartyfinder.ui.adapter.FriendsAdapter
import com.vsa.steampartyfinder.ui.adapter.PlayersDataProvider
import com.vsa.steampartyfinder.ui.base.BaseActivity
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.activity_friends.*
import java.io.Serializable

class FriendsActivity : BaseActivity(), FriendsView {

    companion object {

        private val EXTRA_FRIENDS_LIST = "extra_friends_list"

        fun open(context: Context, friendsList: Serializable) {
            val intent = Intent(context, FriendsActivity::class.java)
            intent.putExtra(EXTRA_FRIENDS_LIST, friendsList)
            context.startActivity(intent)
        }

    }

    private val mPresenter: FriendsPresenter = FriendsPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        initViews()
        mPresenter.onCreate(intent.extras.getSerializable(EXTRA_FRIENDS_LIST))
    }

    private fun initViews() {
        recyclerViewFriends.addItemDecoration(HorizontalDividerItemDecoration.Builder(this)
                .build())
    }

    override fun setFriendsList(dataProvider: PlayersDataProvider) {
        recyclerViewFriends.layoutManager = LinearLayoutManager(this)
        recyclerViewFriends.adapter = FriendsAdapter(dataProvider)
    }

    override fun refreshFriendsList() {
        recyclerViewFriends.adapter?.notifyDataSetChanged()
    }

}
