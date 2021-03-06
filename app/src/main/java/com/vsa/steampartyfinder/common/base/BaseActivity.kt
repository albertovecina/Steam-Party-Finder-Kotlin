package com.vsa.steampartyfinder.common.base

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.vsa.steampartyfinder.R
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Alberto Vecina Sánchez on 5/12/17.
 */
open class BaseActivity : DaggerAppCompatActivity(), BaseView {

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = ProgressDialog(this, R.style.CustomAlertDialogStyle)
        progressDialog.setCancelable(false)
        progressDialog.setMessage("Cargando...")
    }

    override fun showProgress() {
        if (!progressDialog.isShowing)
            progressDialog.show()
    }

    override fun hideProgress() {
        progressDialog.dismiss()
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