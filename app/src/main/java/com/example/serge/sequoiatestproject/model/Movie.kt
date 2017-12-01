package com.example.serge.sequoiatestproject.model

import java.io.Serializable


class Movie constructor(
        var localized_name: String = "",
        var name: String = "",
        var year: Int = 0,
        var rating: Float = 0f,
        var image_url: String = "",
        var description: String = ""
): MovieListItem(), Serializable {
    override fun getType(): Int {
        return MovieListItem.TYPE_FILM
    }
}