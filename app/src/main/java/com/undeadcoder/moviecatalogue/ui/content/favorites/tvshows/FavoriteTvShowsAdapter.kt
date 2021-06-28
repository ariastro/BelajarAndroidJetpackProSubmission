package com.undeadcoder.moviecatalogue.ui.content.favorites.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.undeadcoder.moviecatalogue.BuildConfig
import com.undeadcoder.moviecatalogue.R
import com.undeadcoder.moviecatalogue.data.source.local.entity.TvShowEntity
import com.undeadcoder.moviecatalogue.databinding.ItemMovieBinding

class FavoriteTvShowsAdapter(private val onItemClicked: OnItemClickCallback) : PagedListAdapter<TvShowEntity, FavoriteTvShowsAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    inner class TvShowViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShowEntity: TvShowEntity) {
            with(binding) {
                movieName.text = tvShowEntity.title
                movieOverview.text = tvShowEntity.overview
                movieScore.text = tvShowEntity.voteAverage.toString()
                itemView.setOnClickListener {
                    onItemClicked.onItemClicked(tvShowEntity)
                }
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL + tvShowEntity.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.color.darkGrey)
                            .error(R.drawable.ic_error)
                    )
                    .into(moviePoster)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(tvShowEntity: TvShowEntity)
    }

}