package com.vsa.steampartyfinder.common.di.subcomponent

import com.vsa.steampartyfinder.common.di.scope.PerActivity
import com.vsa.steampartyfinder.feature.friends.di.FriendsModule
import com.vsa.steampartyfinder.feature.friends.view.FriendsActivity
import com.vsa.steampartyfinder.feature.games.di.GamesModule
import com.vsa.steampartyfinder.feature.games.view.GamesActivity
import com.vsa.steampartyfinder.feature.main.di.MainModule
import com.vsa.steampartyfinder.feature.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindsMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [FriendsModule::class])
    abstract fun bindsFriendsActivity(): FriendsActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [GamesModule::class, GamesActivityFragmentsModule::class])
    abstract fun bindsGamesActivity(): GamesActivity

}