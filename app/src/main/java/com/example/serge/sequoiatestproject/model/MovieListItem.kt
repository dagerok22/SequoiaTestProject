package com.example.serge.sequoiatestproject.model

abstract class MovieListItem {
    companion object {
        var TYPE_FILM = 0
        var TYPE_DATE = 1
    }

    abstract fun getType(): Int
}