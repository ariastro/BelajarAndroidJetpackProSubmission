package com.undeadcoder.moviecatalogue.ui.content.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.undeadcoder.moviecatalogue.R
import com.undeadcoder.moviecatalogue.base.BaseFragment
import com.undeadcoder.moviecatalogue.databinding.FragmentFavoriteBinding
import com.undeadcoder.moviecatalogue.ui.main.MainActivity

class FavoriteFragment : BaseFragment() {

    private var _fragmentFavoriteBinding: FragmentFavoriteBinding? = null
    private val binding get() = _fragmentFavoriteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _fragmentFavoriteBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setToolbarTitle(getString(R.string.favorites))

        val sectionsPagerAdapter = SectionsPagerAdapter(requireContext(), childFragmentManager)
        binding?.viewPager?.adapter = sectionsPagerAdapter
        binding?.tabs?.setupWithViewPager(binding?.viewPager)

    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentFavoriteBinding = null
    }

}