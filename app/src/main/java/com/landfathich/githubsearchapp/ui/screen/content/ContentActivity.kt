package com.landfathich.githubsearchapp.ui.screen.content

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.landfathich.githubsearchapp.R
import com.landfathich.githubsearchapp.databinding.ActivityContentBinding
import com.landfathich.githubsearchapp.ui.adapter.FileAdapter
import com.landfathich.githubsearchapp.ui.screen.code.CodeActivity
import com.squareup.picasso.Picasso

class ContentActivity : AppCompatActivity(), FileAdapter.Listener {

    private lateinit var binding: ActivityContentBinding
    private lateinit var viewModel: ContentViewModel
    private lateinit var adapter: FileAdapter
    private lateinit var owner: String
    private lateinit var repo: String
    private var countNext = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater).also { setContentView(it.root) }

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(ContentViewModel::class.java)

        adapter = FileAdapter(this)

        owner = intent.getStringExtra("owner").toString()
        repo = intent.getStringExtra("repo").toString()
        val description = intent.getStringExtra("desc")
        val forksCount = intent.getStringExtra("forks").toString()
        val avatarUrl = intent.getStringExtra("avatar_url").toString()

        getContent(owner, repo)
        setRepoInfo(owner, repo, description, forksCount, avatarUrl)

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

    private fun setRepoInfo(
        owner: String,
        repo: String,
        description: String?,
        forksCount: String,
        avatarUrl: String,
    ) = with(binding) {
        tvLogin.text = owner
        tvName.text = repo
        tvDesc.text = description ?: getString(R.string.no_description_text)
        tvForkCount.text = forksCount
        Picasso.get().load(avatarUrl).into(ivAvatar)
    }

    private fun getContent(owner: String, repo: String, path: String = "") {
        viewModel.getContent(this, owner, repo, path)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun goNextFolder(path: String) {
        countNext++
        Log.d("TEST", "next go folder, count now: $countNext")
        getContent(owner, repo, path)
    }

    override fun openFile(link: String) {
        val intent = Intent(this, CodeActivity::class.java)
        intent.putExtra("link", link)
        startActivity(intent)
    }

    override fun onBackPressed() {
        if (countNext == 0) {
            super.onBackPressed()
        } else {
            countNext--
            Log.d("TEST", "back go folder, count now: $countNext")
            getContent(owner, repo)
        }
    }
}