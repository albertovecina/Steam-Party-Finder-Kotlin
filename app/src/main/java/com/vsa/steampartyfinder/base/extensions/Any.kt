package com.vsa.steampartyfinder.base.extensions

import com.google.gson.Gson

/**
 * Created by Alberto Vecina Sánchez on 5/12/17.
 */
fun Any.toJson(): String = Gson().toJson(this)