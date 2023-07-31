package com.landfathich.githubsearchapp.ui.screens.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.landfathich.githubsearchapp.R
import com.landfathich.githubsearchapp.databinding.ActivityMainBinding
import com.landfathich.githubsearchapp.ui.adapters.UserAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        toggleBtnActive(false)
        adapter = UserAdapter()
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        binding.apply {
            rvUsers.adapter = adapter
            rvUsers.setHasFixedSize(true)
        }

        setUpListeners()

        viewModel.getSearch().observe(this) {
            if (it != null) {
                adapter.addList(it)
                showLoading(false)
            } else {
                adapter.clearList()
            }
        }
    }

    private fun setUpListeners() = with(binding){
        btnSearch.setOnClickListener {
            search()
        }

        etSearch.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER && etSearch.text.toString().length > 2) {
                search()

                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.length > 2) toggleBtnActive(true)
                else toggleBtnActive(false)
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int,
            ) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    private fun toggleBtnActive(state: Boolean) = with(binding) {
        if (state) {
            btnSearch.isEnabled = true
            btnSearch.setImageResource(R.drawable.ic_search)
        } else {
            btnSearch.isEnabled = false
            btnSearch.setImageResource(R.drawable.ic_inactive_search)
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
            etSearch.isEnabled = false
            toggleBtnActive(false)
        } else {
            progressBar.visibility = View.GONE
            etSearch.isEnabled = true
            toggleBtnActive(true)
        }
    }
}