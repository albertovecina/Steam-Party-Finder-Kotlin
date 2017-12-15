package com.vsa.steampartyfinder.ui.adapter.games

import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.vsa.steampartyfinder.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_game.*

/**
 * Created by Alberto Vecina Sánchez on 14/12/17.
 */
class GamesAdapter(gamesDataProvider: GamesDataProvider) : RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {

    private val mGamesDataProvider: GamesDataProvider = gamesDataProvider

    override fun onBindViewHolder(holder: GameViewHolder?, position: Int) {
        holder?.bind(mGamesDataProvider, position)
    }

    override fun getItemCount(): Int {
        return mGamesDataProvider.getGamesListSize()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.row_game, parent, false)
        return GameViewHolder(view)
    }


    class GameViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(gamesDataProvider: GamesDataProvider, position: Int) {
            Picasso.with(containerView.context)
                    .load(gamesDataProvider.getImageUrl(position))
                    .into(imageViewGame)
            textViewGame.text = gamesDataProvider.getName(position)
            with(gamesDataProvider.getGameModes(position)) {
                if (this != null && !isEmpty()) {
                    progressBarGameModes.visibility = View.GONE
                    wrapperViewGameModes.visibility = View.VISIBLE
                    wrapperViewGameModes.removeAllViews()
                    forEach {
                        val icon = AppCompatImageView(containerView.context)
                        icon.setImageResource(it)
                        wrapperViewGameModes.addView(icon)
                    }
                } else {
                    progressBarGameModes.visibility = View.VISIBLE
                    wrapperViewGameModes.visibility = View.GONE
                }
            }
        }
    }
}