package com.vsa.steampartyfinder.ui.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.vsa.steampartyfinder.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_friend.*

/**
 * Created by Alberto Vecina Sánchez on 6/12/17.
 */
class FriendsAdapter(dataProvider: PlayersDataProvider) : RecyclerView.Adapter<FriendsAdapter.FriendViewHolder>() {

    private val mDataProvider = dataProvider

    override fun getItemCount(): Int {
        return mDataProvider.friendsListSize()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FriendViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.row_friend, parent, false)
        return FriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendViewHolder?, position: Int) {
        holder?.bind(mDataProvider, position)
    }

    class FriendViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(dataProvider: PlayersDataProvider, position: Int) {
            wrapperRowFriend.setOnClickListener { dataProvider.onFriendClick(position) }
            if (dataProvider.isFriendSelected(position))
                wrapperRowFriend.setBackgroundColor(Color.BLACK)
            else
                wrapperRowFriend.setBackgroundColor(Color.WHITE)

            textViewPlayerName.text = dataProvider.getFriendName(position)
            Picasso.with(containerView.context).
                    load(dataProvider.getFriendPortraitUrl(position))
                    .into(imageViewPlayerPortrait)
        }

    }

}