package com.mobelite.toytroc.ExpandableRecyclerView

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobelite.toytroc.R

class ParentRecyclerViewAdapter(private val parentItemList: List<ParentItem>, private val context: Context) : RecyclerView.Adapter<ParentRecyclerViewAdapter.ParentRecyclerViewHolder>() {

    private var expandedPosition = -1

    inner class ParentRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val parentImageView: ImageView = itemView.findViewById(R.id.parentLogoIv)
        val parentTitle: TextView = itemView.findViewById(R.id.parentTitleTv)
        val childRecyclerView: RecyclerView = itemView.findViewById(R.id.childRecyclerView)
        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.parent_item, parent, false)
        return ParentRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParentRecyclerViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val parentItem = parentItemList[position]
        holder.parentTitle.text = parentItem.title
        Glide.with(context).load(parentItem.ImageUrl).into(holder.parentImageView)

        if (expandedPosition == position) {
            holder.childRecyclerView.visibility = View.VISIBLE
        } else {
            holder.childRecyclerView.visibility = View.GONE
        }

        holder.constraintLayout.setOnClickListener {
            if (expandedPosition == position) {
                expandedPosition = -1
                notifyItemChanged(position)
            } else {
                if (expandedPosition >= 0) {
                    val prevExpandedPosition = expandedPosition
                    expandedPosition = position
                    notifyItemChanged(prevExpandedPosition)
                } else {
                    expandedPosition = position
                }
                notifyItemChanged(position)
            }
        }

        holder.childRecyclerView.layoutManager = LinearLayoutManager(context)
        holder.childRecyclerView.adapter = ChildRecyclerViewAdapter(parentItem.childitemList, context)
    }

    override fun getItemCount(): Int {
        return parentItemList.size
    }
}
