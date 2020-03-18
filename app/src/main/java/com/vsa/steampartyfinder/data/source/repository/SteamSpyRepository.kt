package com.vsa.steampartyfinder.data.source.repository

import com.vsa.steampartyfinder.common.di.scope.PerApplication
import com.vsa.steampartyfinder.data.model.domain.GameDetails
import com.vsa.steampartyfinder.data.model.mapper.GameDetailsDataMapper
import com.vsa.steampartyfinder.data.source.preferences.PreferencesHelper
import com.vsa.steampartyfinder.data.source.ws.SteamBigPictureApiInterface
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Alberto Vecina Sánchez on 14/12/17.
 */
@PerApplication
class SteamSpyRepository @Inject constructor(private val steamBigPictureApiInterface: SteamBigPictureApiInterface,
                                             private val preferencesHelper: PreferencesHelper) {

    fun observeGameDetails(appId: String): Observable<GameDetails> =
            if (preferencesHelper.isGameCached(appId))
                Observable.create {
                    it.onNext(preferencesHelper.getGameDetails(appId))
                    it.onComplete()
                }
            else steamBigPictureApiInterface.observeGameDetails(appId)
                    .map { appDetails ->
                        GameDetailsDataMapper.transform(appDetails).apply {
                            preferencesHelper.storeGameDetails(this)
                        }
                    }


}