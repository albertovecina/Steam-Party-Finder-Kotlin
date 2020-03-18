package com.vsa.steampartyfinder

import com.vsa.steampartyfinder.common.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by Alberto Vecina Sánchez on 24/11/17.
 */
class SpfApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerApplicationComponent
                    .builder()
                    .application(this)
                    .build()

}