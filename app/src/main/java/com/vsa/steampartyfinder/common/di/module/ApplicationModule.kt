package com.vsa.steampartyfinder.common.di.module

import android.content.Context
import com.vsa.steampartyfinder.SpfApplication
import com.vsa.steampartyfinder.common.di.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    @PerApplication
    fun providesApplicationContext(application: SpfApplication): Context = application

}