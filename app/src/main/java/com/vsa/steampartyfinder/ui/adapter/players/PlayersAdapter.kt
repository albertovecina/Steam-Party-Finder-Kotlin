package com.vsa.steampartyfinder.ui.adapter.players

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.vsa.steampartyfinder.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_player.*

/**
 * Created by Alberto Vecina Sánchez on 6/12/17.
 */
class PlayersAdapter(dataProvider: PlayersDataProvider) : RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder>() {

    private val mDataProvider = dataProvider

    override fun getItemCount(): Int {
        return mDataProvider.friendsListSize()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(mDataProvider, position)
    }

    class PlayerViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(dataProvider: PlayersDataProvider, position: Int) {
            wrapperRowFriend.setOnClickListener { dataProvider.onFriendClick(position) }
            if (dataProvider.isFriendSelected(position)) {
                wrapperRowFriend.setBackgroundResource(R.color.colorSelected)
            } else {
                wrapperRowFriend.setBackgroundColor(Color.TRANSPARENT)
            }

            textViewPlayerName.text = dataProvider.getFriendName(position)
            Picasso.get()
                    .load(dataProvider.getFriendPortraitUrl(position))
                    .placeholder(R.drawable.avatar_placeholder)
                    .into(imageViewPlayerPortrait)
        }

    }

}