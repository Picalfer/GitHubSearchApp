package com.landfathich.githubsearchapp.ui.screens.main

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.landfathich.githubsearchapp.data.api.RetrofitClient
import com.landfathich.githubsearchapp.data.model.Item
import com.landfathich.githubsearchapp.data.model.Repo
import com.landfathich.githubsearchapp.data.model.User
import com.landfathich.githubsearchapp.ui.screens.ErrorActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val listItems = MutableLiveData<ArrayList<Item>>()

    fun setSearch(context: Context, query: String) {
        listItems.postValue(null)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val list = ArrayList<Item>()
                val userResponse = RetrofitClient.apiInstance.getAllUsersByName(query)
                val userList = userResponse.body()?.items as ArrayList<User>
                userList.forEach {
                    it.id_name = it.login
                }
                list.addAll(userList)

                val repoResponse = RetrofitClient.apiInstance.getAllReposByName(query)
                val repoList = repoResponse.body()?.items as ArrayList<Repo>
                repoList.forEach {
                    it.id_name = it.name
                }
                list.addAll(repoList)
                list.sortBy { it.id_name }
                listItems.postValue(list)
            } catch (e: Throwable) {
                val intent = Intent(context, ErrorActivity::class.java)
                intent.putExtra("error", e.toString())
                context.startActivity(intent)
                listItems.postValue(ArrayList())
            }
        }
    }

    fun getSearch(): LiveData<ArrayList<Item>> {
        return listItems
    }
}