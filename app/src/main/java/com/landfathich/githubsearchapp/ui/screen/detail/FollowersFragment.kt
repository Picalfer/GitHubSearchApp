package com.landfathich.githubsearchapp.ui.screen.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.landfathich.githubsearchapp.R
import com.landfathich.githubsearchapp.databinding.FragmentFollowBinding
import com.landfathich.githubsearchapp.ui.adapter.SearchAdapter

class FollowersFragment: Fragment(R.layout.fragment_follow) {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: SearchAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _binding = FragmentFollowBinding.bind(view)

        adapter = SearchAdapter()

        binding.apply {
            rvUsers.setHasFixedSize(true)
            rvUsers.adapter = adapter
        }

        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)
        viewModel.setListFollowers(this.requireContext(), username)
        viewModel.getListFollowers().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.items = it
                showLoading(false)
            }
        }
    }

    private fun showLoading(state: Boolean) = with(binding) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}