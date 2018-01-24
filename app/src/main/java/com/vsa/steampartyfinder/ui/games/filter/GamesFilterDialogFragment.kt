package com.vsa.steampartyfinder.ui.games.filter

import android.animation.Animator
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vsa.steampartyfinder.R
import com.vsa.steampartyfinder.presentation.games.filter.GamesFilterPresenter
import com.vsa.steampartyfinder.presentation.games.filter.GamesFilterPresenterImpl
import kotlinx.android.synthetic.main.dialog_fragment_games_filter.*
import java.io.Serializable


/**
 * Created by Alberto Vecina Sánchez on 15/12/17.
 */
class GamesFilterDialogFragment : DialogFragment(), GamesFilterView {

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

    lateinit var animator: Animator

    private val mPresenter: GamesFilterPresenter = GamesFilterPresenterImpl(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.dialog_fragment_games_filter, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        mPresenter.onViewCreated(arguments?.getSerializable(ARG_GAME_MODES))
    }

    private fun initViews() {
        buttonDialogFilterOk.setOnClickListener { mPresenter.onAcceptButtonClick() }
        buttonDialogFilterCancel.setOnClickListener { mPresenter.onCancelButtonClick() }
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