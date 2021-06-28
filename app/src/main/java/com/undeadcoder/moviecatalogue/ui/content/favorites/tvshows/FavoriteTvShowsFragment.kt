package com.undeadcoder.moviecatalogue.ui.content.favorites.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.undeadcoder.moviecatalogue.base.BaseFragment
import com.undeadcoder.moviecatalogue.data.source.local.entity.TvShowEntity
import com.undeadcoder.moviecatalogue.databinding.FragmentFavoriteTvShowsBinding
import com.undeadcoder.moviecatalogue.ui.content.favorites.FavoriteViewModel
import com.undeadcoder.moviecatalogue.ui.detail.DetailActivity
import com.undeadcoder.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_DATA_TYPE
import com.undeadcoder.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_ID
import com.undeadcoder.moviecatalogue.utils.Constants.TV_SHOWS
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTvShowsFragment : BaseFragment(), FavoriteTvShowsAdapter.OnItemClickCallback {

    private var _fragmentFavoriteTvShowsBinding: FragmentFavoriteTvShowsBinding? = null
    private val binding get() = _fragmentFavoriteTvShowsBinding

    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var adapter: FavoriteTvShowsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _fragmentFavoriteTvShowsBinding = FragmentFavoriteTvShowsBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            adapter = FavoriteTvShowsAdapter(this)

            viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, {
                if (!it.isNullOrEmpty()) {
                    binding?.noData?.visibility = View.GONE
                    binding?.rvFavoriteTvShows?.visibility = View.VISIBLE
                    adapter.submitList(it)
                    adapter.notifyDataSetChanged()
                } else {
                    binding?.noData?.visibility = View.VISIBLE
                    binding?.rvFavoriteTvShows?.visibility = View.GONE
                }
            })

            binding?.rvFavoriteTvShows?.setHasFixedSize(true)
            binding?.rvFavoriteTvShows?.adapter = adapter
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentFavoriteTvShowsBinding = null
    }

    override fun onItemClicked(tvShowEntity: TvShowEntity) {
        startActivity(Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(EXTRA_ID, tvShowEntity.id.toString())
            putExtra(EXTRA_DATA_TYPE, TV_SHOWS)
        })
    }

}