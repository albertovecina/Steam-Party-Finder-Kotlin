package com.vsa.steampartyfinder.feature.games.filter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.vsa.steampartyfinder.R
import com.vsa.steampartyfinder.feature.games.filter.presenter.GamesFilterPresenter
import dagger.android.support.DaggerAppCompatDialogFragment
import kotlinx.android.synthetic.main.dialog_fragment_games_filter.*
import java.io.Serializable
import javax.inject.Inject


/**
 * Created by Alberto Vecina Sánchez on 15/12/17.
 */
class GamesFilterDialogFragment : DaggerAppCompatDialogFragment(), GamesFilterView {

    companion object {

        private val TAG: String = GamesFilterDialogFragment::class.java.simpleName

        private val ARG_GAME_MODES = "arg_game_modes"

        fun show(fragmentManager: FragmentManager, gameModes: Serializable) {
            val args = Bundle()
            args.putSerializable(ARG_GAME_MODES, gameModes)

            val dialogFragment = GamesFilterDialogFragment()
            dialogFragment.arguments = args
            dialogFragment.show(fragmentManager, TAG)
        }

    }

    @Inject
    lateinit var presenter: GamesFilterPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.dialog_fragment_games_filter, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        presenter.onViewCreated(arguments?.getSerializable(ARG_GAME_MODES))
    }

    private fun initViews() {
        buttonDialogFilterOk.setOnClickListener { presenter.onAcceptButtonClick() }
        buttonDialogFilterCancel.setOnClickListener { presenter.onCancelButtonClick() }
    }

    override fun setSinglePlayerChecked() {
        checkBoxSingle.isChecked = true
    }

    override fun setMultiplayerChecked() {
        checkBoxMulti.isChecked = true
    }

    override fun setCoopChecked() {
        checkBoxCoop.isChecked = true
    }

    override fun isSinglePlayerChecked(): Boolean {
        return checkBoxSingle.isChecked
    }

    override fun isSingleMultiplayerChecked(): Boolean {
        return checkBoxMulti.isChecked
    }

    override fun isSingleCoopChecked(): Boolean {
        return checkBoxCoop.isChecked
    }

    override fun notifyFiltersSelected(gameModes: Serializable) {
        if (activity is GamesFilterCallback)
            with(activity as GamesFilterCallback) {
                onFilterSelected(gameModes)
            }
    }

    interface GamesFilterCallback {
        fun onFilterSelected(gameModes: Serializable)
    }

}