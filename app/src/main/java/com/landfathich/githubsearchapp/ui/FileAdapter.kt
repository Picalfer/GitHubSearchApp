package com.landfathich.githubsearchapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.landfathich.githubsearchapp.R
import com.landfathich.githubsearchapp.data.model.RepoContentItem
import com.landfathich.githubsearchapp.databinding.FileItemBinding

class FileAdapter : RecyclerView.Adapter<FileAdapter.FileViewHolder>() {
    private val list = ArrayList<RepoContentItem>()

    fun addList(items: ArrayList<RepoContentItem>) {
        list.addAll(items)
        notifyDataSetChanged()
    }

    class FileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = FileItemBinding.bind(itemView)
        fun bind(file: RepoContentItem) {
            itemView.setOnClickListener {

            }
            binding.apply {
                tvName.text = file.name
                if (file.type == "file") {
                    ivIcon.setImageResource(R.drawable.ic_file)
                } else if (file.type == "dir"){
                    ivIcon.setImageResource(R.drawable.ic_folder)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileAdapter.FileViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.file_item, parent, false)
        return FileViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.bind(list[position])
    }
}