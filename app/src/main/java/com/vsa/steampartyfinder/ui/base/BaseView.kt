package com.vsa.steampartyfinder.ui.base

import android.view.View

/**
 * Created by Alberto Vecina Sánchez on 19/12/17.
 */
interface BaseView {

    fun showProgress()

    fun hideProgress()

    fun showInfoMessage(message: String)

    fun showErrorMessage(message: String)

    fun showErrorMessage(view: View, message: String)

}