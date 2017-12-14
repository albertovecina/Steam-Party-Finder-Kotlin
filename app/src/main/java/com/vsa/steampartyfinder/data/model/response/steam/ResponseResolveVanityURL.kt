package com.vsa.steampartyfinder.data.model.response.steam

/**
 * Created by Alberto Vecina Sánchez on 4/12/17.
 */

data class ContentResolveVanityURL(val steamid: String, val success: String)

data class ResponseResolveVanityURL(val response: ContentResolveVanityURL)
