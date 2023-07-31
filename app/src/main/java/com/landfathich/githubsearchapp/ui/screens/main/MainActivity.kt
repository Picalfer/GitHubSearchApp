package com.landfathich.githubsearchapp.ui.screens.main

import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.landfathich.githubsearchapp.databinding.ActivityMainBinding
import com.landfathich.githubsearchapp.ui.adapters.UserAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        binding.apply {
            rvUsers.adapter = adapter
            rvUsers.setHasFixedSize(true)

            btnSearch.setOnClickListener {
                search()
            }

            etSearch.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    search()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        viewModel.getSearch().observe(this) {
            if (it != null) {
                adapter.addList(it)
                showLoading(false)
            } else {
                adapter.clearList()
            }
        }
    }

    private fun search() {
        binding.apply {
            val query = etSearch.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            viewModel.setSearch(query)
        }
    }

    private fun showLoading(state: Boolean) = with(binding) {
        if (state) {
            progressBar.visibility = View.VISIBLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                etSearch.isEnabled = false
                btnSearch.focusable = View.NOT_FOCUSABLE
            }
        } else {
            progressBar.visibility = View.GONE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                etSearch.isEnabled = true
                btnSearch.focusable = View.FOCUSABLE
            }
        }
    }
}