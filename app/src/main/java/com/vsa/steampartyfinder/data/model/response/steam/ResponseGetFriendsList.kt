package com.vsa.steampartyfinder.data.model.response.steam

import com.google.gson.annotations.SerializedName

/**
 * Created by Alberto Vecina Sánchez on 4/12/17.
 */
data class Friend(@SerializedName("steamid") val steamId: String, val relationship: String, @SerializedName("friend_since") val friendSince: String)

data class FriendsList(val friends: ArrayList<Friend>)

data class ResponseGetFriendsList(val friendslist: FriendsList)