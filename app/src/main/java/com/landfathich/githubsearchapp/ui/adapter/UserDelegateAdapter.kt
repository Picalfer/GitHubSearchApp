package com.landfathich.githubsearchapp.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.landfathich.githubsearchapp.R
import com.landfathich.githubsearchapp.data.model.Item
import com.landfathich.githubsearchapp.data.model.User
import com.landfathich.githubsearchapp.databinding.UserItemBinding
import com.squareup.picasso.Picasso

class UserDelegateAdapter :
    AbsListItemAdapterDelegate<User, Item, UserDelegateAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = UserItemBinding.bind(itemView)
        fun bind(user: User) {
            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(user.html_url))
                itemView.context.startActivity(intent)
            }
            binding.apply {
                Picasso.get().load(user.avatar_url).into(ivAvatar)
                tvLogin.text = user.login
                tvType.text = user.type
            }
        }
    }

    override fun isForViewType(item: Item, items: MutableList<Item>, position: Int): Boolean {
        return item is User
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(item: User, holder: ViewHolder, payloads: MutableList<Any>) {
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.item_appearance_animation)
        holder.bind(item)
    }
}