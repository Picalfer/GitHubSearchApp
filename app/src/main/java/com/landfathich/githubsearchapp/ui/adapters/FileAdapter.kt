package com.landfathich.githubsearchapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.landfathich.githubsearchapp.R
import com.landfathich.githubsearchapp.data.model.RepoContentItem
import com.landfathich.githubsearchapp.databinding.FileItemBinding

class FileAdapter(private val listener: Listener) :
    RecyclerView.Adapter<FileAdapter.FileViewHolder>() {
    private val list = ArrayList<RepoContentItem>()

    fun addList(items: ArrayList<RepoContentItem>) {
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun setList(items: ArrayList<RepoContentItem>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    class FileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = FileItemBinding.bind(itemView)
        fun bind(file: RepoContentItem, listener: Listener) {
            binding.apply {
                tvName.text = file.name
                if (file.type == "file") {
                    ivIcon.setImageResource(R.drawable.ic_file)

                    itemView.setOnClickListener {
                        listener.openFile(file.html_url)
                    }
                } else if (file.type == "dir") {
                    ivIcon.setImageResource(R.drawable.ic_folder)

                    itemView.setOnClickListener {
                        listener.goNextFolder(file.path)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.file_item, parent, false)
        return FileViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    interface Listener {
        fun goNextFolder(path: String)
        fun openFile(link: String)
    }
}