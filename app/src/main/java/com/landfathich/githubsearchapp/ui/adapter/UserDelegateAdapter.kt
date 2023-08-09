package com.landfathich.githubsearchapp.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.landfathich.githubsearchapp.R
import com.landfathich.githubsearchapp.Utils
import com.landfathich.githubsearchapp.data.model.Item
import com.landfathich.githubsearchapp.data.model.User
import com.landfathich.githubsearchapp.databinding.UserItemBinding
import com.landfathich.githubsearchapp.ui.screen.detail.DetailUserActivity
import com.squareup.picasso.Picasso

class UserDelegateAdapter :
    AbsListItemAdapterDelegate<User, Item, UserDelegateAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = UserItemBinding.bind(itemView)
        fun bind(user: User) {
            itemView.setOnClickListener {
                Intent(itemView.context, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, user.login)
                    itemView.context.startActivity(it)
                }
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
        Utils.setAnimationForListItem(holder)
        holder.bind(item)
    }
}