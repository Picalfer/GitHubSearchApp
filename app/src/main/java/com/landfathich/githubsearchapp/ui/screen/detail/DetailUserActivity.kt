package com.landfathich.githubsearchapp.ui.screen.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.landfathich.githubsearchapp.databinding.ActivityDetailUserBinding
import com.squareup.picasso.Picasso

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater).also { setContentView(it.root) }

        val username = intent.getStringExtra(EXTRA_USERNAME) ?: ""

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailUserViewModel::class.java)

        viewModel.setUserDetail(this, username)
        viewModel.getUserDetail().observe(this) {
            if (it != null) {
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvFollowers.text = "${it.followers} Followers"
                    tvFollowing.text = "${it.following} Followers"
                    Picasso.get().load(it.avatar_url).into(ivProfile)
                }
            }
        }

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }
}