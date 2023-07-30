package com.landfathich.githubsearchapp.ui.content

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.landfathich.githubsearchapp.databinding.ActivityContentBinding
import com.landfathich.githubsearchapp.ui.main.MainViewModel

class ContentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContentBinding
    private lateinit var viewModel: ContentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater).also { setContentView(it.root) }

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(ContentViewModel::class.java)

        val owner = intent.getStringExtra("owner")
        val repo = intent.getStringExtra("repo")

        if (owner != null && repo != null) {
            getContent(owner, repo)
        }

    }

    private fun getContent(owner: String, repo: String) {
        viewModel.getContent(owner, repo)
    }
}