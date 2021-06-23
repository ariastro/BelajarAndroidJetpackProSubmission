package com.undeadcoder.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.undeadcoder.moviecatalogue.BuildConfig
import com.undeadcoder.moviecatalogue.R
import com.undeadcoder.moviecatalogue.base.BaseActivity
import com.undeadcoder.moviecatalogue.data.DetailEntity
import com.undeadcoder.moviecatalogue.databinding.ActivityDetailBinding
import com.undeadcoder.moviecatalogue.utils.Constants.MOVIES
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    private var isMovies = false

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
        const val EXTRA_DATA_TYPE = "EXTRA_DATA_TYPE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
        setupUI()

    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            progress.show()
            binding.scrollView.visibility = View.GONE
        } else {
            progress.dismiss()
            binding.scrollView.visibility = View.VISIBLE
        }
    }

    private fun getData() {
        setLoading(true)
        val id = intent.getStringExtra(EXTRA_ID) ?: ""
        isMovies = intent.getStringExtra(EXTRA_DATA_TYPE) == MOVIES
        viewModel.getDetailContent(isMovies, id).observe(this, {
            showData(it)
        })
    }

    private fun setupUI() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = if (isMovies) getString(R.string.detail_movie) else getString(R.string.detail_tv_show)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showData(data: DetailEntity) {
        setLoading(false)
        binding.title.text = data.title
        binding.releaseDate.text = data.releaseDate
        binding.score.text = data.voteAverage.toString()
        var tempGenre = ""
        data.genres.forEach {
            tempGenre += "$it, "
        }
        binding.genre.text = tempGenre.take(tempGenre.length - 2)
        binding.overview.text = data.overview

        Glide.with(this)
            .load(BuildConfig.IMAGE_URL + data.backdropPath)
            .apply(
                RequestOptions.placeholderOf(R.color.darkGrey)
                    .error(R.drawable.ic_error)
            )
            .into(binding.banner)

        Glide.with(this)
            .load(BuildConfig.IMAGE_URL + data.posterPath)
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