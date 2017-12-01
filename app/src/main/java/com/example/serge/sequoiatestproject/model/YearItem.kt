package com.example.serge.sequoiatestproject.model


class YearItem(
        var date: String = ""
) : MovieListItem() {
    override fun getType(): Int {
        return MovieListItem.TYPE_DATE
    }
}