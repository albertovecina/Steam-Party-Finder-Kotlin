package com.vsa.steampartyfinder.data.model.response

/**
 * Created by Alberto Vecina Sánchez on 5/12/17.
 */
data class Player(val steamid: String,
                  val personaname: String,
                  val profileurl: String,
                  val avatar: String,
                  val avatarmedium: String,
                  val avatarfull: String,
                  val personastate: Int,
                  val communityvisibilitystate: Int,
                  val profilestate: Int,
                  val lastlogoff: Long,
                  val commentpermission: Int
)

data class ContentGetPlayerSummaries(val players: List<Player>)

data class ResponseGetPlayerSummaries(val response: ContentGetPlayerSummaries)