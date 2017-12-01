package com.example.serge.sequoiatestproject.ui

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import com.example.serge.sequoiatestproject.R
import com.example.serge.sequoiatestproject.helper.ItemClickSupport
import com.example.serge.sequoiatestproject.model.Movie
import com.example.serge.sequoiatestproject.model.MovieListItem
import com.example.serge.sequoiatestproject.model.YearItem
import com.example.serge.sequoiatestproject.util.DataProvider
import kotlinx.android.synthetic.main.action_bar_movie_list_layout.view.*
import kotlinx.android.synthetic.main.activity_movies.*
import java.util.*
import kotlin.collections.ArrayList

class MoviesActivity : AppCompatActivity() {

    private lateinit var adapter: MovieAdapter

    var dataSet: ArrayList<MovieListItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        setUpAppBar()
        setUpData()
        setUpMovieRecycler()
    }

    private fun setUpAppBar() {
        val myToolbar = findViewById<View>(R.id.my_toolbar) as Toolbar
        setSupportActionBar(myToolbar)
        with(supportActionBar!!) {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            val view = layoutInflater.inflate(R.layout.action_bar_movie_list_layout, null)
            view.title.text = getString(R.string.movies_appbar_title)
            customView = view
        }
    }

    private fun setUpMovieRecycler() {
        adapter = MovieAdapter(dataSet)
        recycler_view_films.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recycler_view_films.adapter = adapter
        ItemClickSupport.addTo(recycler_view_films).setOnItemClickListener { _, position, v ->
            if (adapter.getItemViewType(position) == MovieListItem.TYPE_FILM) {
                gotoMovieDetails(adapter.getItem(position) as Movie)
            }
        }
    }

    private fun setUpData() {
        val films = DataProvider.getResponse()
        val treeMap = TreeMap<Int, ArrayList<Movie>>()
        films.forEach { film ->
            if (treeMap.containsKey(film.year)) {
                treeMap[film.year]!!.add(film)
            } else {
                val newList = ArrayList<Movie>()
                newList.add(film)
                treeMap.put(film.year, newList)
            }
        }
        val sortedMap = treeMap.toSortedMap()
        for (key in sortedMap.keys) {
            dataSet.add(YearItem(key.toString()))
            sortedMap[key]!!.forEach { film -> dataSet.add(film) }
        }
    }

    private fun gotoMovieDetails(movie: Movie) {
        startActivity(MovieDetailsActivity.getIntent(applicationContext, movie))
    }
}
