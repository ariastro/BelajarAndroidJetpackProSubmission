package com.undeadcoder.moviecatalogue.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.undeadcoder.moviecatalogue.BuildConfig
import com.undeadcoder.moviecatalogue.R
import com.undeadcoder.moviecatalogue.base.BaseActivity
import com.undeadcoder.moviecatalogue.data.source.local.entity.MovieEntity
import com.undeadcoder.moviecatalogue.data.source.local.entity.TvShowEntity
import com.undeadcoder.moviecatalogue.databinding.ActivityDetailBinding
import com.undeadcoder.moviecatalogue.databinding.ContentDetailBinding
import com.undeadcoder.moviecatalogue.utils.Constants.MOVIES
import com.undeadcoder.moviecatalogue.vo.Status
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailContentBinding: ContentDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    private var isMovies = false

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
        const val EXTRA_DATA_TYPE = "EXTRA_DATA_TYPE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = binding.content
        setContentView(binding.root)

        setupUI()
        setupClickListeners()
        getData()
        observeData()

    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            progress.show()
            binding.root.visibility = View.GONE
        } else {
            progress.dismiss()
            binding.root.visibility = View.VISIBLE
        }
    }

    private fun getData() {
        setLoading(true)
        val id = intent.getStringExtra(EXTRA_ID) ?: ""
        isMovies = intent.getStringExtra(EXTRA_DATA_TYPE) == MOVIES
        viewModel.getDetailContent(isMovies, id)
    }

    private fun observeData() {
        if (isMovies) {
            viewModel.detailMovie.observe(this, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> setLoading(true)
                        Status.ERROR -> {
                            setLoading(false)
                            Toast.makeText(
                                this,
                                getString(R.string.terjadi_kesalahan),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        Status.SUCCESS -> {
                            setLoading(false)
                            if (it.data != null) {
                                showData(it.data)
                            }
                        }
                    }
                }
            })
        } else {
            viewModel.detailTvShow.observe(this, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> setLoading(true)
                        Status.ERROR -> {
                            setLoading(false)
                            Toast.makeText(
                                this,
                                getString(R.string.terjadi_kesalahan),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        Status.SUCCESS -> {
                            setLoading(false)
                            if (it.data != null) {
                                showData(it.data)
                            }
                        }
                    }
                }
            })
        }
    }

    private fun setupUI() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT)
        val typeface = ResourcesCompat.getFont(this, R.font.poppins_semibold)
        binding.collapsingToolbar.apply {
            setCollapsedTitleTypeface(typeface)
            setExpandedTitleTypeface(typeface)
        }
        supportActionBar?.title = if (isMovies) getString(R.string.detail_movie) else getString(R.string.detail_tv_show)
    }

    private fun showData(data: MovieEntity) {
        setLoading(false)
        detailContentBinding.title.text = data.title
        detailContentBinding.releaseDate.text = data.releaseDate
        detailContentBinding.score.text = data.voteAverage.toString()
        detailContentBinding.genre.text = data.genres
        detailContentBinding.overview.text = data.overview
        setFavoriteState(data.isFavorite)

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
            .into(detailContentBinding.poster)
    }

    private fun showData(data: TvShowEntity) {
        setLoading(false)
        detailContentBinding.title.text = data.title
        detailContentBinding.releaseDate.text = data.releaseDate
        detailContentBinding.score.text = data.voteAverage.toString()
        detailContentBinding.genre.text = data.genres
        detailContentBinding.overview.text = data.overview
        setFavoriteState(data.isFavorite)

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
            .into(detailContentBinding.poster)
    }

    private fun setupClickListeners() {
        binding.fabFavorite.setOnClickListener {
            if (isMovies) {
                viewModel.setFavoriteMovie()
            } else {
                viewModel.setFavoriteTvShow()
            }
        }
    }

    private fun setFavoriteState(isFavorite: Boolean) {
        if (isFavorite) {
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite_filled)
        } else {
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}