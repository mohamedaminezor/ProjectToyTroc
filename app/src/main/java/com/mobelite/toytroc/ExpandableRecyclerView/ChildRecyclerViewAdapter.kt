package com.mobelite.toytroc.ExpandableRecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobelite.toytroc.R

class ChildRecyclerViewAdapter(private val childList: List<ChildItem>, private val context: Context) :
    RecyclerView.Adapter<ChildRecyclerViewAdapter.ChildViewHolder>() {

    inner class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.ChildImage)
        var checkbox : CheckBox = itemView.findViewById(R.id.ChildcheckBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.child_item, parent, false)
        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val childItem = childList[position]
        Glide.with(context).load(childItem.ImageUrl).into(holder.imageView)
        holder.checkbox.text = childItem.title
    }

    override fun getItemCount(): Int {
        return childList.size
    }
}