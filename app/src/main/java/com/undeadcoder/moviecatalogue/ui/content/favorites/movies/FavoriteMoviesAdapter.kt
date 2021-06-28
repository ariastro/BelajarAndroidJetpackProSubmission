package com.undeadcoder.moviecatalogue.ui.content.favorites.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.undeadcoder.moviecatalogue.BuildConfig
import com.undeadcoder.moviecatalogue.R
import com.undeadcoder.moviecatalogue.data.source.local.entity.MovieEntity
import com.undeadcoder.moviecatalogue.databinding.ItemMovieBinding

class FavoriteMoviesAdapter(private val onItemClicked: OnItemClickCallback) : PagedListAdapter<MovieEntity, FavoriteMoviesAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                movieName.text = movie.title
                movieOverview.text = movie.overview
                movieScore.text = movie.voteAverage.toString()
                itemView.setOnClickListener {
                    onItemClicked.onItemClicked(movie)
                }
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL + movie.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.color.darkGrey)
                            .error(R.drawable.ic_error)
                    )
                    .into(moviePoster)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(movie: MovieEntity)
    }

}