package com.landfathich.githubsearchapp.ui.content

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.landfathich.githubsearchapp.databinding.ActivityContentBinding
import com.landfathich.githubsearchapp.ui.FileAdapter

class ContentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContentBinding
    private lateinit var viewModel: ContentViewModel
    private lateinit var adapter: FileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater).also { setContentView(it.root) }

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(ContentViewModel::class.java)

        adapter = FileAdapter()
        adapter.notifyDataSetChanged()

        val owner = intent.getStringExtra("owner")
        val repo = intent.getStringExtra("repo")

        if (owner != null && repo != null) {
            getContent(owner, repo)
        }

        showLoading(true)

        binding.apply {
            rvFiles.adapter = adapter
            rvFiles.setHasFixedSize(true)
        }

        viewModel.getSearch().observe(this) {
            if (it != null) {
                adapter.addList(it)
                showLoading(false)
            }
        }
    }

    private fun getContent(owner: String, repo: String) {
        viewModel.getContent(owner, repo)
    }


    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}