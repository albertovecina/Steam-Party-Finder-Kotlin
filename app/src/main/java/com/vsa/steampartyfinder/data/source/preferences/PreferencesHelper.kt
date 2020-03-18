package com.vsa.steampartyfinder.data.source.preferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.vsa.steampartyfinder.data.model.domain.GameDetails
import javax.inject.Inject

class PreferencesHelper @Inject constructor(context: Context) {

    companion object {
        const val KEY_PREFERENCES = "SPF_PREFERENCES"
    }

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            KEY_PREFERENCES, Context.MODE_PRIVATE)
    private val gson: Gson = Gson()

    fun isGameCached(appId: String): Boolean = sharedPreferences.contains(appId)

    fun getGameDetails(appId: String): GameDetails =
            gson.fromJson(sharedPreferences.getString(appId, ""), GameDetails::class.java)

    fun storeGameDetails(game: GameDetails) {
        sharedPreferences.edit()
                .putString(game.appId, gson.toJson(game))
                .apply()
    }

}