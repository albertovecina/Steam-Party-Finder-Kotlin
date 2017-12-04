package com.vsa.steampartyfinder

import android.app.Application
import android.content.Context

/**
 * Created by Alberto Vecina Sánchez on 24/11/17.
 */
class SpfApplication : Application() {

    companion object {
        lateinit var context: Context

    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}