package com.vsa.steampartyfinder.data.model.mapper

import com.vsa.steampartyfinder.data.model.domain.GameDetails
import com.vsa.steampartyfinder.data.model.response.steamspy.ResponseAppDetails

/**
 * Created by Alberto Vecina Sánchez on 14/12/17.
 */
object GameDetailsDataMapper {

    fun transform(responseAppDetails: ResponseAppDetails): GameDetails {
        val gameDetails = GameDetails()
        gameDetails.appId = responseAppDetails.appid

        with(responseAppDetails.tags) {
            if (containsKey(ResponseAppDetails.TAG_SINGLE_PLAYER))
                gameDetails.gameModes.add(GameDetails.GameMode.SINGLE)
            if (containsKey(ResponseAppDetails.TAG_MULTIPLAYER))
                gameDetails.gameModes.add(GameDetails.GameMode.MULTIPLAYER)
            if (containsKey(ResponseAppDetails.TAG_CO_OP))
                gameDetails.gameModes.add(GameDetails.GameMode.COOP)
        }
        return gameDetails
    }

    fun transform(responseAppDetails: com.vsa.steampartyfinder.data.model.response.bigpicture.ResponseAppDetails): GameDetails {
        val gameDetails = GameDetails()
        responseAppDetails.entries.forEach {
            gameDetails.appId = it.key
            var gameModes: MutableList<GameDetails.GameMode>? =
                    it.value.data?.categories?.mapNotNull {
                        when (it.id) {
                            2 -> GameDetails.GameMode.SINGLE
                            1 -> GameDetails.GameMode.MULTIPLAYER
                            9 -> GameDetails.GameMode.COOP
                            else -> null
                        }
                    }?.toMutableList()
            if (gameModes == null)
                gameModes = ArrayList()
            gameDetails.gameModes = gameModes
        }
        return gameDetails
    }

}