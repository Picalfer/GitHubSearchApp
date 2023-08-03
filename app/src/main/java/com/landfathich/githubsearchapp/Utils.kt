package com.landfathich.githubsearchapp

import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView

object Utils {

    fun setAnimationForListItem(holder: RecyclerView.ViewHolder) {
        holder.itemView.animation = if (holder.absoluteAdapterPosition % 2 == 0)
            AnimationUtils.loadAnimation(
                holder.itemView.context,
                R.anim.search_item_animation_left
            )
        else
            AnimationUtils.loadAnimation(
                holder.itemView.context,
                R.anim.search_item_animation_right
            )
    }
}