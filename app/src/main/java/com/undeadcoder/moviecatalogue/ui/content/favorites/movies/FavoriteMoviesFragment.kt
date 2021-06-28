package com.undeadcoder.moviecatalogue.ui.content.favorites.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.undeadcoder.moviecatalogue.base.BaseFragment
import com.undeadcoder.moviecatalogue.data.source.local.entity.MovieEntity
import com.undeadcoder.moviecatalogue.databinding.FragmentFavoriteMoviesBinding
import com.undeadcoder.moviecatalogue.ui.content.favorites.FavoriteViewModel
import com.undeadcoder.moviecatalogue.ui.detail.DetailActivity
import com.undeadcoder.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_DATA_TYPE
import com.undeadcoder.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_ID
import com.undeadcoder.moviecatalogue.utils.Constants.MOVIES
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMoviesFragment : BaseFragment(), FavoriteMoviesAdapter.OnItemClickCallback {

    private var _fragmentFavoriteMoviesBinding: FragmentFavoriteMoviesBinding? = null
    private val binding get() = _fragmentFavoriteMoviesBinding

    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var adapter: FavoriteMoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _fragmentFavoriteMoviesBinding = FragmentFavoriteMoviesBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            adapter = FavoriteMoviesAdapter(this)

            viewModel.getFavoriteMovies().observe(viewLifecycleOwner, {
                if (!it.isNullOrEmpty()) {
                    binding?.noData?.visibility = View.GONE
                    binding?.rvFavoriteMovies?.visibility = View.VISIBLE
                    adapter.submitList(it)
                    adapter.notifyDataSetChanged()
                } else {
                    binding?.noData?.visibility = View.VISIBLE
                    binding?.rvFavoriteMovies?.visibility = View.GONE
                }
            })

            binding?.rvFavoriteMovies?.setHasFixedSize(true)
            binding?.rvFavoriteMovies?.adapter = adapter
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentFavoriteMoviesBinding = null
    }

    override fun onItemClicked(movie: MovieEntity) {
        startActivity(Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(EXTRA_ID, movie.id.toString())
            putExtra(EXTRA_DATA_TYPE, MOVIES)
        })
    }

}