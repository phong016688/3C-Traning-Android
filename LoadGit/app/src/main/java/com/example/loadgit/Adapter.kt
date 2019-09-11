package com.example.loadgit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item.view.*

class Adapter(context: Context, list: List<Data>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    var list: ArrayList<Data> = ArrayList()
    var context: Context? = null
    init {
        this.context =  context
        this.list = list as ArrayList<Data>
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onbind(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onbind(position: Int) {
            itemView.textid.text = list[position].id
            itemView.textname.text = list[position].name
            Picasso.get().load(list[position].image)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .resize(50, 50)
                .into(itemView.imagerView)
        }
    }
}