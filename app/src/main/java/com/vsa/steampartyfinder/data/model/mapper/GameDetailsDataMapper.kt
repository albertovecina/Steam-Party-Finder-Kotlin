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

}