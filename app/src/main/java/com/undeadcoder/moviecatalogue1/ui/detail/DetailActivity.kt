package com.undeadcoder.moviecatalogue1.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.undeadcoder.moviecatalogue1.R
import com.undeadcoder.moviecatalogue1.data.DataEntity
import com.undeadcoder.moviecatalogue1.databinding.ActivityDetailBinding
import com.undeadcoder.moviecatalogue1.utils.Constants.MOVIES

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    private var isMovies = false

    private lateinit var data: DataEntity

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
        const val EXTRA_DATA_TYPE = "EXTRA_DATA_TYPE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentData()
        setupUI()
        showData()

    }

    private fun getIntentData() {
        val id = intent.getStringExtra(EXTRA_ID)!!
        isMovies = intent.getStringExtra(EXTRA_DATA_TYPE) == MOVIES
        data = if (isMovies) {
            viewModel.setMovieId(id)
            viewModel.getMovie()
        } else {
            viewModel.setTvShowId(id)
            viewModel.getTVShow()
        }
    }

    private fun setupUI() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = if (isMovies) getString(R.string.detail_movie) else getString(R.string.detail_tv_show)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showData() {
        binding.title.text = data.title
        binding.releaseDate.text = data.releaseDate
        binding.score.text = data.score
        binding.genre.text = data.genre
        binding.overview.text = data.overview

        Glide.with(this)
            .load(data.banner)
            .apply(
                RequestOptions.placeholderOf(R.color.darkGrey)
                    .error(R.drawable.ic_error)
            )
            .into(binding.banner)
        Glide.with(this)
            .load(data.poster)
            .apply(
                RequestOptions.placeholderOf(R.color.darkGrey)
                    .error(R.drawable.ic_error)
            )
            .into(binding.poster)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}