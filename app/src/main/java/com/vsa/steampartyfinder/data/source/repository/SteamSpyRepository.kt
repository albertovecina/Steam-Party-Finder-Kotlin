package com.vsa.steampartyfinder.data.source.repository

import com.vsa.steampartyfinder.data.model.domain.GameDetails
import com.vsa.steampartyfinder.data.model.mapper.GameDetailsDataMapper
import com.vsa.steampartyfinder.data.source.ws.SteamSpyClient
import io.reactivex.Observable

/**
 * Created by Alberto Vecina Sánchez on 14/12/17.
 */
object SteamSpyRepository {

    fun observeGameDetails(appId: String): Observable<GameDetails> {
        return SteamSpyClient.create().observeGameDetails(appId)
                .map { appDetails -> GameDetailsDataMapper.transform(appDetails) }
    }

}