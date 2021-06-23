package com.undeadcoder.moviecatalogue.ui.content.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.undeadcoder.moviecatalogue.base.BaseFragment
import com.undeadcoder.moviecatalogue.data.MovieEntity
import com.undeadcoder.moviecatalogue.databinding.FragmentMoviesBinding
import com.undeadcoder.moviecatalogue.ui.detail.DetailActivity
import com.undeadcoder.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_DATA_TYPE
import com.undeadcoder.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_ID
import com.undeadcoder.moviecatalogue.utils.Constants.MOVIES
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : BaseFragment(), MovieAdapter.OnItemClickCallback {

    private lateinit var binding: FragmentMoviesBinding
    private val viewModel: MoviesViewModel by viewModel()
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            setLoading(true)
            adapter = MovieAdapter(this)
            viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                setLoading(false)
                adapter.setMovies(movies)
                adapter.notifyDataSetChanged()
            })

            binding.rvMovies.setHasFixedSize(true)
            binding.rvMovies.adapter = adapter
        }
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            progress.show()
            binding.rvMovies.visibility = View.GONE
        } else {
            progress.dismiss()
            binding.rvMovies.visibility = View.VISIBLE
        }
    }

    override fun onItemClicked(movie: MovieEntity) {
        startActivity(Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(EXTRA_ID, movie.id.toString())
            putExtra(EXTRA_DATA_TYPE, MOVIES)
        })
    }

}