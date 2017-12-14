package com.vsa.steampartyfinder.data.model.response.steamspy

/**
 * Created by Alberto Vecina Sánchez on 14/12/17.
 */
data class ResponseAppDetails(val appid: String, val name: String, val developer: String, val publisher: String, val tags: Map<String, Long>) {

    companion object {
        val TAG_SINGLE_PLAYER: String = "Singleplayer"
        val TAG_MULTIPLAYER: String = "Multiplayer"
        val TAG_CO_OP: String = "Co-op"
    }

}