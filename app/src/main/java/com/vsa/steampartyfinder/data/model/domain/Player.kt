package com.vsa.steampartyfinder.data.model.domain

import java.io.Serializable

/**
 * Created by Alberto Vecina Sánchez on 5/12/17.
 */
data class Player(val steamId: String, val name: String, val portraitUrl: String) : Serializable