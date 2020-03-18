package com.vsa.steampartyfinder.feature.games.filter.di

import com.vsa.steampartyfinder.common.di.scope.PerFragment
import com.vsa.steampartyfinder.feature.games.filter.presenter.GamesFilterPresenter
import com.vsa.steampartyfinder.feature.games.filter.presenter.GamesFilterPresenterImpl
import com.vsa.steampartyfinder.feature.games.filter.view.GamesFilterDialogFragment
import com.vsa.steampartyfinder.feature.games.filter.view.GamesFilterView
import dagger.Module
import dagger.Provides

@Module
class GamesFilterModule {

    @Provides
    @PerFragment
    fun providesView(fragment: GamesFilterDialogFragment): GamesFilterView = fragment

    @Provides
    @PerFragment
    fun providesPresenter(presenter: GamesFilterPresenterImpl): GamesFilterPresenter = presenter

}