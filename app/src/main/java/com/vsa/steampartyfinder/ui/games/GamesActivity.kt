package com.vsa.steampartyfinder.ui.games

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.vsa.steampartyfinder.R
import com.vsa.steampartyfinder.presentation.games.GamesPresenter
import com.vsa.steampartyfinder.presentation.games.GamesPresenterImpl
import com.vsa.steampartyfinder.ui.adapter.games.GamesAdapter
import com.vsa.steampartyfinder.ui.adapter.games.GamesDataProvider
import com.vsa.steampartyfinder.ui.base.BaseActivity
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.activity_games.*
import java.io.Serializable

class GamesActivity : BaseActivity(), GamesView {

    companion object {
        private val EXTRA_GAMES_LIST = "extra_games_list"

        fun open(context: Context, gamesList: Serializable) {
            val intent = Intent(context, GamesActivity::class.java)
            intent.putExtra(EXTRA_GAMES_LIST, gamesList)
            context.startActivity(intent)
        }

    }


    private val mGamesPresenter: GamesPresenter = GamesPresenterImpl(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games)
        initViews()
        mGamesPresenter.onCreate(intent.getSerializableExtra(EXTRA_GAMES_LIST))
    }

    private fun initViews() {
        recyclerViewGames.layoutManager = LinearLayoutManager(this)
        recyclerViewGames.addItemDecoration(HorizontalDividerItemDecoration.Builder(this).build())
    }

    override fun setGamesList(dataProvider: GamesDataProvider) {
        recyclerViewGames.adapter = GamesAdapter(dataProvider)
    }

}
