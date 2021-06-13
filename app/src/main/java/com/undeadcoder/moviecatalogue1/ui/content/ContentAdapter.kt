package com.undeadcoder.moviecatalogue1.ui.content

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.undeadcoder.moviecatalogue1.R
import com.undeadcoder.moviecatalogue1.data.DataEntity
import com.undeadcoder.moviecatalogue1.databinding.ItemMovieBinding

class ContentAdapter(private val onItemClicked: ContentCallback) : RecyclerView.Adapter<ContentAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<DataEntity>()

    fun setMovies(movies: List<DataEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dataEntity: DataEntity) {
            with(binding) {
                movieName.text = dataEntity.title
                movieGenre.text = dataEntity.genre
                movieScore.text = dataEntity.score
                itemView.setOnClickListener {
                    onItemClicked.onItemClicked(dataEntity)
                }
                Glide.with(itemView.context)
                    .load(dataEntity.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.color.darkGrey)
                            .error(R.drawable.ic_error)
                    )
                    .into(moviePoster)
            }
        }
    }
}