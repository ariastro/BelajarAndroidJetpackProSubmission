package com.undeadcoder.moviecatalogue1.ui.content

import com.undeadcoder.moviecatalogue1.data.DataEntity

interface ContentCallback {
    fun onItemClicked(dataEntity: DataEntity)
}
