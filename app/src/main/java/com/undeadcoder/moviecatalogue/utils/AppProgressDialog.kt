package com.undeadcoder.moviecatalogue.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.undeadcoder.moviecatalogue.R

class AppProgressDialog(context : Context) : Dialog(context){

    init {
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.layout_progress_dialog)
    }
}