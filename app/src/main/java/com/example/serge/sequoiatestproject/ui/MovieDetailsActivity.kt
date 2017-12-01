package com.example.serge.sequoiatestproject.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.bumptech.glide.Glide
import com.example.serge.sequoiatestproject.R
import com.example.serge.sequoiatestproject.model.Movie
import kotlinx.android.synthetic.main.action_bar_movie_details_layout.view.*
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    lateinit var movie: Movie

    companion object {
        private val MOVIE_KEY = "movie_intent_bundle"

        fun getIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_KEY, movie)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        movie = intent.getSerializableExtra(MOVIE_KEY) as Movie
        setUpAppBar()
        setUpUi()

    }

    private fun setUpAppBar() {
        val myToolbar = findViewById<View>(R.id.my_toolbar) as Toolbar
        setSupportActionBar(myToolbar)
        with(supportActionBar!!) {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            val view = layoutInflater.inflate(R.layout.action_bar_movie_details_layout, null)
            view.back_button.setOnClickListener { onBackPressed() }
            view.title.text = movie.localized_name
            customView = view
        }
    }

    private fun setUpUi() {

        name.text = movie.name
        year.text = String.format(getString(R.string.movie_details_year), movie.year)
        ratingText.text = getString(R.string.movie_details_rating)
        ratingNumber.text = movie.rating.toString()
        description.text = movie.description
        Glide.with(applicationContext)
                .load(movie.image_url)
                .into(thumbnail)
    }
}
