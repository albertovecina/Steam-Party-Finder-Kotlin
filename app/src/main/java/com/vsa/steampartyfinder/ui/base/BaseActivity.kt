package com.vsa.steampartyfinder.ui.base

import android.app.ProgressDialog
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.vsa.steampartyfinder.R

/**
 * Created by Alberto Vecina Sánchez on 5/12/17.
 */
open class BaseActivity : AppCompatActivity(), BaseView {

    var progressDialog: ProgressDialog? = null

    override fun showProgress() {
        if (progressDialog == null)
            progressDialog = ProgressDialog.show(this, null, "Cargando...")
        else
            progressDialog?.show()
    }

    override fun hideProgress() {
        progressDialog?.dismiss()
    }

    override fun showInfoMessage(message: String) {
        val snackBar = Snackbar.make(window.decorView.findFocus(), message, Snackbar.LENGTH_LONG)
        snackBar.view.setBackgroundResource(R.color.colorButton)
        snackBar.show()
    }

    override fun showErrorMessage(message: String) {
        showErrorMessage(window.decorView.findFocus(), message)
    }

    override fun showErrorMessage(view: View, message: String) {
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snackBar.view.setBackgroundResource(R.color.errorBackground)
        snackBar.show()
    }

}