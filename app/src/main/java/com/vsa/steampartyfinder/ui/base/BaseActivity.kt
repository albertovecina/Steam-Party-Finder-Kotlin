package com.vsa.steampartyfinder.ui.base

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity

/**
 * Created by Alberto Vecina Sánchez on 5/12/17.
 */
open class BaseActivity : AppCompatActivity() {

    companion object {
        var progressDialog: ProgressDialog? = null
    }

    fun showProgress() {
        if (progressDialog == null)
            progressDialog = ProgressDialog.show(this, null, "Cargando...")
        else
            progressDialog?.show()
    }

    fun hideProgress() {
        progressDialog?.hide()
    }

}