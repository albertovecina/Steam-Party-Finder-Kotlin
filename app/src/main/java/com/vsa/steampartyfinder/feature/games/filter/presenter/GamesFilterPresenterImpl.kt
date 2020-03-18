package com.vsa.steampartyfinder.feature.games.filter.presenter

import com.vsa.steampartyfinder.data.model.domain.GameDetails
import com.vsa.steampartyfinder.feature.games.filter.view.GamesFilterView
import java.io.Serializable
import javax.inject.Inject

/**
 * Created by Alberto Vecina Sánchez on 15/12/17.
 */
class GamesFilterPresenterImpl @Inject constructor(private val view: GamesFilterView) : GamesFilterPresenter {

    override fun onViewCreated(gameModes: Serializable?) {
        if (gameModes != null)
            if (gameModes is List<*>)
                gameModes.filterIsInstance(GameDetails.GameMode::class.java).forEach {
                    when (it) {
                        GameDetails.GameMode.SINGLE -> view.setSinglePlayerChecked()
                        GameDetails.GameMode.MULTIPLAYER -> view.setMultiplayerChecked()
                        GameDetails.GameMode.COOP -> view.setCoopChecked()
                    }
                }
    }

    override fun onAcceptButtonClick() {
        val gameModes: ArrayList<GameDetails.GameMode> = ArrayList()
        if (view.isSinglePlayerChecked())
            gameModes.add(GameDetails.GameMode.SINGLE)
        if (view.isSingleMultiplayerChecked())
            gameModes.add(GameDetails.GameMode.MULTIPLAYER)
        if (view.isSingleCoopChecked())
            gameModes.add(GameDetails.GameMode.COOP)
        view.notifyFiltersSelected(gameModes)
        view.dismiss()
    }

    override fun onCancelButtonClick() {
        view.dismiss()
    }

}