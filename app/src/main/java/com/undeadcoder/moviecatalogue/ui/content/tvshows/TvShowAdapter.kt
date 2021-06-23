package com.undeadcoder.moviecatalogue.ui.content.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.undeadcoder.moviecatalogue.BuildConfig
import com.undeadcoder.moviecatalogue.R
import com.undeadcoder.moviecatalogue.data.TvShowEntity
import com.undeadcoder.moviecatalogue.databinding.ItemMovieBinding

class TvShowAdapter(private val onItemClicked: OnItemClickCallback) : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private var listTvShow = ArrayList<TvShowEntity>()

    fun setTvShows(tvShows: List<TvShowEntity>?) {
        if (tvShows.isNullOrEmpty()) return
        this.listTvShow.clear()
        this.listTvShow.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class TvShowViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShowEntity: TvShowEntity) {
            with(binding) {
                movieName.text = tvShowEntity.name
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