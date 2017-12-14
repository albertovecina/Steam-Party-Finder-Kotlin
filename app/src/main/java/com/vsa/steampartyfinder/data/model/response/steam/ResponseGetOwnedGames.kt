package com.vsa.steampartyfinder.data.model.response.steam

/**
 * Created by Alberto Vecina Sánchez on 11/12/17.
 */
data class OwnedGame(val appid: String, val name: String, val playtime_forever: Long, val img_icon_url: String, val img_logo_url: String, val has_community_visible_stats: Boolean)

data class ContentGetOwnedGames(val game_count: Int, val games: List<OwnedGame>)

data class ResponseGetOwnedGames(val response: ContentGetOwnedGames)