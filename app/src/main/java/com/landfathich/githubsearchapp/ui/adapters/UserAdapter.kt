package com.landfathich.githubsearchapp.ui.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.landfathich.githubsearchapp.R
import com.landfathich.githubsearchapp.data.model.Item
import com.landfathich.githubsearchapp.data.model.Repo
import com.landfathich.githubsearchapp.data.model.User
import com.landfathich.githubsearchapp.databinding.RepoItemBinding
import com.landfathich.githubsearchapp.databinding.UserItemBinding
import com.landfathich.githubsearchapp.ui.screens.content.ContentActivity
import com.squareup.picasso.Picasso

const val ITEM_VIEW_TYPE_USER = 0
const val ITEM_VIEW_TYPE_REPO = 1

class UserAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = ArrayList<Item>()

    fun setList(items: ArrayList<Item>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun clearList() {
        list.clear()
    }

    fun addList(items: ArrayList<Item>) {
        list.addAll(items)
        notifyDataSetChanged()
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RepoItemBinding.bind(itemView)
        fun bind(repo: Repo) {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, ContentActivity::class.java)
                intent.putExtra("owner", repo.owner.login)
                intent.putExtra("repo", repo.name)
                intent.putExtra("forks", repo.forks_count.toString())
                intent.putExtra("desc", repo.description)
                intent.putExtra("avatar_url", repo.owner.avatar_url)
                itemView.context.startActivity(intent)
            }
            binding.apply {
                tvForkCount.text = repo.forks_count.toString()
                tvName.text = repo.name
                tvDesc.text = repo.description
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] is User) ITEM_VIEW_TYPE_USER
        else if (list[position] is Repo) ITEM_VIEW_TYPE_REPO
        else throw IllegalArgumentException("Invalid item view type")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ITEM_VIEW_TYPE_USER) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
            return UserViewHolder(view)
        } else if (viewType == ITEM_VIEW_TYPE_REPO) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
            return RepoViewHolder(view)
        } else throw IllegalArgumentException("Invalid viewType ")
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.item_appearance_animation)
        if (getItemViewType(position) == ITEM_VIEW_TYPE_USER) {
            val h = holder as UserViewHolder
            val user = list[position] as User
            h.bind(user)
        } else if (getItemViewType(position) == ITEM_VIEW_TYPE_REPO) {
            val h = holder as RepoViewHolder
            val repo = list[position] as Repo
            h.bind(repo)
        }
    }
}