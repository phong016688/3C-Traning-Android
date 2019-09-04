package com.phong.loaddatahttp

import android.graphics.Bitmap
import android.widget.ImageView

class ItemData(imageView: Bitmap?,text:String? ) {
    var imagerView: Bitmap? = null
    var text: String? = null
    init {
        this.imagerView = imageView
        this.text = text
    }
}