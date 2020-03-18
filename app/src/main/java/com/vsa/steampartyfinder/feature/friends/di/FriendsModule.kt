package com.vsa.steampartyfinder.feature.friends.di

import com.vsa.steampartyfinder.common.di.scope.PerActivity
import com.vsa.steampartyfinder.feature.friends.view.FriendsActivity
import com.vsa.steampartyfinder.feature.friends.view.FriendsView
import com.vsa.steampartyfinder.feature.friends.presenter.FriendsPresenter
import com.vsa.steampartyfinder.feature.friends.presenter.FriendsPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class FriendsModule {

    @PerActivity
    @Provides
    fun providesView(activity: FriendsActivity): FriendsView = activity

    @PerActivity
    @Provides
    fun providesPresenter(presenter: FriendsPresenterImpl): FriendsPresenter = presenter

}