package com.vsa.steampartyfinder.ui.friends

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.view.ViewAnimationUtils
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

    private var fabInAnimator: Animator? = null
    private var fabOutAnimator: Animator? = null

    private val presenter: FriendsPresenter = FriendsPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        initViews()
            intent.extras?.let {
                presenter.onCreate(
                        it.getString(EXTRA_STEAM_ID, ""),
                        it.getSerializable(EXTRA_FRIENDS_LIST))
            }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        recyclerViewFriends.layoutManager = LinearLayoutManager(this)
        recyclerViewFriends.addItemDecoration(HorizontalDividerItemDecoration.Builder(this)
                .build())
        fabFindGames.setOnClickListener { presenter.onFindButtonClick() }
    }

    override fun showFindButton() {
        fabOutAnimator?.run { if (isRunning) removeAllListeners() }

        if (fabFindGames.visibility != View.VISIBLE) {
            val radius = fabFindGames.width / 2
            fabInAnimator = ViewAnimationUtils.createCircularReveal(fabFindGames, radius, radius, 0f, radius.toFloat())
            fabInAnimator?.addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(p0: Animator?) {
                }

                override fun onAnimationEnd(p0: Animator?) {
                }

                override fun onAnimationCancel(p0: Animator?) {
                }

                override fun onAnimationStart(p0: Animator?) {
                    fabFindGames.visibility = View.VISIBLE
                }

            })
            fabInAnimator?.start()
        }
    }

    override fun hideFindButton() {
        fabInAnimator?.run { if (isRunning) removeAllListeners() }

        if (fabFindGames.visibility != View.INVISIBLE) {
            val radius = fabFindGames.width / 2
            fabOutAnimator = ViewAnimationUtils.createCircularReveal(fabFindGames, radius, radius, radius.toFloat(), 0f)
            fabOutAnimator?.addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(p0: Animator?) {
                }

                override fun onAnimationEnd(p0: Animator?) {
                    fabFindGames.visibility = View.INVISIBLE
                }

                override fun onAnimationCancel(p0: Animator?) {
                }

                override fun onAnimationStart(p0: Animator?) {
                }

            })
            fabOutAnimator?.start()
        }
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

    override fun showErrorMessage(message: String) {
        super.showErrorMessage(wrapperFriendsContent, message)
    }
}
