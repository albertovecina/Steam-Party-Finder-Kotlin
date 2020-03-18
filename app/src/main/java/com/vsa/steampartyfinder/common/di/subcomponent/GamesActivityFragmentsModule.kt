package com.vsa.steampartyfinder.common.di.subcomponent

import com.vsa.steampartyfinder.common.di.scope.PerFragment
import com.vsa.steampartyfinder.feature.games.filter.di.GamesFilterModule
import com.vsa.steampartyfinder.feature.games.filter.view.GamesFilterDialogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class GamesActivityFragmentsModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [GamesFilterModule::class])
    abstract fun bindsGamesFilterDialogFragment(): GamesFilterDialogFragment

}