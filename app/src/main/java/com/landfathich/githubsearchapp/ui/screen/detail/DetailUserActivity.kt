package com.landfathich.githubsearchapp.ui.screen.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.landfathich.githubsearchapp.R
import com.landfathich.githubsearchapp.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater).also { setContentView(it.root) }
    }
}