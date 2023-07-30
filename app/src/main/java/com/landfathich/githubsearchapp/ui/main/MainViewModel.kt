package com.landfathich.githubsearchapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.landfathich.githubsearchapp.data.api.RetrofitClient
import com.landfathich.githubsearchapp.data.model.Item
import com.landfathich.githubsearchapp.data.model.Repo
import com.landfathich.githubsearchapp.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val listItems = MutableLiveData<ArrayList<Item>>()

    fun setSearch(query: String) {
        listItems.postValue(null)
        CoroutineScope(Dispatchers.IO).launch {
            val userResponse = RetrofitClient.apiInstance.getAllUsersByName(query)
            val userList = userResponse.items as ArrayList<Item>
            with(userList as ArrayList<User>) {
                sortBy { it.login.lowercase() }
            }
            listItems.postValue(userList)

            val repoResponse = RetrofitClient.apiInstance.getAllReposByName(query)
            val repoList = repoResponse.items as ArrayList<Item>
            with(repoList as ArrayList<Repo>) {
                sortBy { it.name.lowercase() }
            }
            listItems.postValue(repoList)
        }
    }

    fun getSearch(): LiveData<ArrayList<Item>> {
        return listItems
    }
}