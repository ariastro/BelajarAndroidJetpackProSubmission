package com.undeadcoder.moviecatalogue.base

import android.os.Bundle
import android.os.StrictMode
import androidx.fragment.app.Fragment
import com.undeadcoder.moviecatalogue.utils.AppProgressDialog

abstract class BaseFragment: Fragment() {

    lateinit var progress : AppProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupProgress()
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
    }

    private fun setupProgress() {
        progress = AppProgressDialog(requireContext())
        progress.setCancelable(false)
        progress.setCanceledOnTouchOutside(false)
    }

}