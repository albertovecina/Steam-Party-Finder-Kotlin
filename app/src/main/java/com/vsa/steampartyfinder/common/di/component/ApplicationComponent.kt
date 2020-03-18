package com.vsa.steampartyfinder.common.di.component

import com.vsa.steampartyfinder.SpfApplication
import com.vsa.steampartyfinder.common.di.subcomponent.ActivitiesModule
import com.vsa.steampartyfinder.common.di.module.ApplicationModule
import com.vsa.steampartyfinder.common.di.module.NetworkModule
import com.vsa.steampartyfinder.common.di.scope.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [AndroidInjectionModule::class,
    ApplicationModule::class,
    NetworkModule::class,
    ActivitiesModule::class])
@PerApplication
interface ApplicationComponent : AndroidInjector<SpfApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: SpfApplication): Builder

        fun build(): ApplicationComponent

    }

}