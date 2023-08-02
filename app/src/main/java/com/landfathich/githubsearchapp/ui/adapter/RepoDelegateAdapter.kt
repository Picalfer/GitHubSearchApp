package com.landfathich.githubsearchapp.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.landfathich.githubsearchapp.R
import com.landfathich.githubsearchapp.data.model.Item
import com.landfathich.githubsearchapp.data.model.Repo
import com.landfathich.githubsearchapp.databinding.RepoItemBinding
import com.landfathich.githubsearchapp.ui.screen.content.ContentActivity

class RepoDelegateAdapter :
    AbsListItemAdapterDelegate<Repo, Item, RepoDelegateAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

    override fun isForViewType(item: Item, items: MutableList<Item>, position: Int): Boolean {
        return item is Repo
    }

    override fun onCreateViewHolder(parent: ViewGroup): RepoDelegateAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        item: Repo,
        holder: RepoDelegateAdapter.ViewHolder,
        payloads: MutableList<Any>,
    ) {
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.item_appearance_animation)
        holder.bind(item)
    }
}