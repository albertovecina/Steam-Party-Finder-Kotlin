package com.vsa.steampartyfinder.feature.main.di

import com.vsa.steampartyfinder.common.di.scope.PerActivity
import com.vsa.steampartyfinder.feature.main.view.MainActivity
import com.vsa.steampartyfinder.feature.main.view.MainView
import com.vsa.steampartyfinder.feature.main.presenter.MainPresenter
import com.vsa.steampartyfinder.feature.main.presenter.MainPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @PerActivity
    @Provides
    fun providesView(activity: MainActivity): MainView = activity

    @PerActivity
    @Provides
    fun providesPresenter(presenter: MainPresenterImpl): MainPresenter = presenter

}