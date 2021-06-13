package com.undeadcoder.moviecatalogue1.ui.content.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.undeadcoder.moviecatalogue1.data.DataEntity
import com.undeadcoder.moviecatalogue1.databinding.FragmentTvShowsBinding
import com.undeadcoder.moviecatalogue1.ui.content.ContentAdapter
import com.undeadcoder.moviecatalogue1.ui.content.ContentCallback
import com.undeadcoder.moviecatalogue1.ui.detail.DetailActivity
import com.undeadcoder.moviecatalogue1.ui.detail.DetailActivity.Companion.EXTRA_DATA_TYPE
import com.undeadcoder.moviecatalogue1.ui.detail.DetailActivity.Companion.EXTRA_ID
import com.undeadcoder.moviecatalogue1.utils.Constants.TV_SHOWS

class TvShowsFragment : Fragment(), ContentCallback {

    private lateinit var binding: FragmentTvShowsBinding
    private val viewModel: TvShowsViewModel by viewModels()
    private lateinit var adapter: ContentAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movies = viewModel.getTvShows()
            adapter = ContentAdapter(this)
            adapter.setMovies(movies)
            binding.rvTvShows.setHasFixedSize(true)
            binding.rvTvShows.adapter = adapter
        }
    }

    override fun onItemClicked(dataEntity: DataEntity) {
        startActivity(Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(EXTRA_ID, dataEntity.id)
            putExtra(EXTRA_DATA_TYPE, TV_SHOWS)
        })
    }

}