package com.vsa.steampartyfinder.presentation.games.filter

import com.vsa.steampartyfinder.data.model.domain.GameDetails
import com.vsa.steampartyfinder.ui.games.filter.GamesFilterView
import java.io.Serializable

/**
 * Created by Alberto Vecina Sánchez on 15/12/17.
 */
class GamesFilterPresenterImpl(view: GamesFilterView) : GamesFilterPresenter {

    private val mView: GamesFilterView = view

    override fun onViewCreated(gameModes: Serializable) {
        if (gameModes is List<*>)
            gameModes.filterIsInstance(GameDetails.GameMode::class.java).forEach {
                when (it) {
                    GameDetails.GameMode.SINGLE -> mView.setSinglePlayerChecked()
                    GameDetails.GameMode.MULTIPLAYER -> mView.setMultiplayerChecked()
                    GameDetails.GameMode.COOP -> mView.setCoopChecked()
                }
            }

    }

    override fun onAcceptButtonClick() {
        val gameModes: ArrayList<GameDetails.GameMode> = ArrayList()
        if (mView.isSinglePlayerChecked())
            gameModes.add(GameDetails.GameMode.SINGLE)
        if (mView.isSingleMultiplayerChecked())
            gameModes.add(GameDetails.GameMode.MULTIPLAYER)
        if (mView.isSingleCoopChecked())
            gameModes.add(GameDetails.GameMode.COOP)
        mView.notifyFiltersSelected(gameModes)
        mView.dismiss()
    }

    override fun onCancelButtonClick() {
        mView.dismiss()
    }

}