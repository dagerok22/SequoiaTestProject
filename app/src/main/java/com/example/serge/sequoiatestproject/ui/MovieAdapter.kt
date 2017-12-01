package com.example.serge.sequoiatestproject.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.serge.sequoiatestproject.inflate
import com.example.serge.sequoiatestproject.R
import com.example.serge.sequoiatestproject.model.Movie
import com.example.serge.sequoiatestproject.model.MovieListItem
import com.example.serge.sequoiatestproject.model.YearItem
import kotlinx.android.synthetic.main.recycler_view_item_date.view.*
import kotlinx.android.synthetic.main.recycler_view_item_movie.view.*
import java.util.*

class MovieAdapter(private var dataSet: ArrayList<MovieListItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var context: Context

    override fun getItemViewType(position: Int): Int {
        return dataSet[position].getType()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == MovieListItem.TYPE_DATE) {
            val v = parent!!.inflate(R.layout.recycler_view_item_date)
            return YearViewHolder(v)
        }
        val v: View = parent!!.inflate(R.layout.recycler_view_item_movie)
        context = parent.context
        return MovieViewHolder(v)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val item = dataSet[position]
        when (holder!!.itemViewType) {
            MovieListItem.TYPE_FILM -> {
                val currentItem =  item as Movie
                val currentHolder = holder as MovieViewHolder
                currentHolder.localizedName.text = currentItem.localized_name
                currentHolder.name.text = currentItem.name
                currentHolder.rating.text = currentItem.rating.toString()
            }
            MovieListItem.TYPE_DATE -> {
                (holder as YearViewHolder).year.text = (item as YearItem).date
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class YearViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        var year = view.year
    }

    class MovieViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        var name = view.name
        var localizedName = view.localized_name
        var rating = view.rating
    }

    fun getItem(position: Int): MovieListItem {
        return dataSet[position]
    }

}