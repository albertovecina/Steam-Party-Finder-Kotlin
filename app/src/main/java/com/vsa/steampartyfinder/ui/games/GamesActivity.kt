package com.vsa.steampartyfinder.ui.games

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.vsa.steampartyfinder.R
import com.vsa.steampartyfinder.presentation.games.GamesPresenter
import com.vsa.steampartyfinder.presentation.games.GamesPresenterImpl
import com.vsa.steampartyfinder.ui.adapter.games.GamesAdapter
import com.vsa.steampartyfinder.ui.adapter.games.GamesDataProvider
import com.vsa.steampartyfinder.ui.base.BaseActivity
import com.vsa.steampartyfinder.ui.custom.FabActionMenu
import com.vsa.steampartyfinder.ui.games.filter.GamesFilterDialogFragment
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


    private val mPresenter: GamesPresenter = GamesPresenterImpl(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games)
        initViews()
        mPresenter.onCreate(intent.getSerializableExtra(EXTRA_GAMES_LIST))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        recyclerViewGames.layoutManager = LinearLayoutManager(this)
        recyclerViewGames.addItemDecoration(HorizontalDividerItemDecoration.Builder(this).build())
        fabActionMenuFilters.setOnAcceptButtonClickListener(object : FabActionMenu.OnAcceptButtonClickListener {
            override fun onAcceptButtonClick(status: Array<Boolean>) {
                mPresenter.onFiltersChange(status)
            }

        })
    }

    override fun setGamesList(dataProvider: GamesDataProvider) {
        recyclerViewGames.adapter = GamesAdapter(dataProvider)
    }

    override fun showFiltersDialog(gameModes: Serializable) {
        GamesFilterDialogFragment.show(supportFragmentManager, gameModes)
    }

    override fun getSinglePlayerIcon(): Int {
        return R.drawable.ic_single_player
    }

    override fun getMultiPlayerIcon(): Int {
        return R.drawable.ic_multiplayer
    }

    override fun getCoopIcon(): Int {
        return R.drawable.ic_coop
    }

    override fun refreshList() {
        recyclerViewGames.adapter?.notifyDataSetChanged()
    }

    override fun refreshList(position: Int) {
        recyclerViewGames.adapter?.notifyItemChanged(position)
    }

    override fun removeItemAt(position: Int) {
        recyclerViewGames.adapter?.notifyItemRemoved(position)
    }

}
