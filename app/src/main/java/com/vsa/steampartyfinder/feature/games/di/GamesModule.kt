package com.vsa.steampartyfinder.feature.games.di

import com.vsa.steampartyfinder.common.di.scope.PerActivity
import com.vsa.steampartyfinder.common.di.scope.PerFragment
import com.vsa.steampartyfinder.feature.games.filter.di.GamesFilterModule
import com.vsa.steampartyfinder.feature.games.filter.view.GamesFilterDialogFragment
import com.vsa.steampartyfinder.feature.games.presenter.GamesPresenter
import com.vsa.steampartyfinder.feature.games.presenter.GamesPresenterImpl
import com.vsa.steampartyfinder.feature.games.view.GamesActivity
import com.vsa.steampartyfinder.feature.games.view.GamesView
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
class GamesModule {

    @PerActivity
    @Provides
    fun providesView(activity: GamesActivity): GamesView = activity

    @PerActivity
    @Provides
    fun providesPresenter(presenter: GamesPresenterImpl): GamesPresenter = presenter

}