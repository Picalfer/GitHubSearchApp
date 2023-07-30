package com.landfathich.githubsearchapp.ui.content

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.landfathich.githubsearchapp.databinding.ActivityContentBinding
import com.landfathich.githubsearchapp.ui.FileAdapter

class ContentActivity : AppCompatActivity(), FileAdapter.Listener {

    private lateinit var binding: ActivityContentBinding
    private lateinit var viewModel: ContentViewModel
    private lateinit var adapter: FileAdapter
    private lateinit var owner: String
    private lateinit var repo: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater).also { setContentView(it.root) }

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(ContentViewModel::class.java)

        adapter = FileAdapter(this)
        adapter.notifyDataSetChanged()

        owner = intent.getStringExtra("owner").toString()
        repo = intent.getStringExtra("repo").toString()

        getContent(owner, repo)

        showLoading(true)

        binding.apply {
            rvFiles.adapter = adapter
            rvFiles.setHasFixedSize(true)
        }

        viewModel.getSearch().observe(this) {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        }
    }

    private fun getContent(owner: String, repo: String, path: String = "") {
        viewModel.getContent(owner, repo, path)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onClick(path: String) {
        getContent(owner, repo, path)
    }
}