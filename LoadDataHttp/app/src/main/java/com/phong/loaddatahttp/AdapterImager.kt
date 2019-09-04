package com.phong.loaddatahttp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class AdapterImager(list: List<ItemData>?, context: Context?) : RecyclerView.Adapter<AdapterImager.ViewHolder>() {
    var listData: List<ItemData>? = null
    var context : Context? = null
    init {
        var listData: List<ItemData>? = listData
        var context : Context? = context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.item_data, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData?.size!! ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imager : ImageView? = null
        var text : String? = null
        fun onBind(position: Int) {
            imager?.setImageBitmap(listData?.get(position)?.imagerView)
            text = listData?.get(position)?.text
        }
    }
}