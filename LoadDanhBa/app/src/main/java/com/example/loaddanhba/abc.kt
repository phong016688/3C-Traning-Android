package com.example.loaddanhba

import android.content.Context
import android.widget.SimpleCursorAdapter

class abc {
    internal var c: Context? = null
    fun abc() {
        val mAdapter = SimpleCursorAdapter(
            c,
            R.layout.item,
            null,
            arrayOf("name", "photo", "details"),
            intArrayOf(R.id.name, R.id.imageview, R.id.detail),
            0
        )
    }
}
