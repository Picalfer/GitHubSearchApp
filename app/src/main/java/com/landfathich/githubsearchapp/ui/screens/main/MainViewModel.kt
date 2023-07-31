package com.landfathich.githubsearchapp.ui.screens.main

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.landfathich.githubsearchapp.data.api.RetrofitClient
import com.landfathich.githubsearchapp.data.model.Item
import com.landfathich.githubsearchapp.data.model.Repo
import com.landfathich.githubsearchapp.data.model.RepoResponse
import com.landfathich.githubsearchapp.data.model.User
import com.landfathich.githubsearchapp.ui.screens.ErrorActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class MainViewModel : ViewModel() {
    val listItems = MutableLiveData<ArrayList<Item>>()

    fun setSearch(context: Context, query: String) {
        listItems.postValue(null)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userResponse = RetrofitClient.apiInstance.getAllUsersByName(query)
                if (userResponse.errorBody() != null) {
                    Log.d("TEST", userResponse.errorBody()?.string().toString())
                }
                val userList = userResponse.body()?.items as ArrayList<Item>
                with(userList as ArrayList<User>) {
                    sortBy { it.login.lowercase() }
                }
                listItems.postValue(userList)

                val repoResponse = RetrofitClient.apiInstance.getAllReposByName(query)
                val repoList = repoResponse.body()?.items as ArrayList<Item>
                with(repoList as ArrayList<Repo>) {
                    sortBy { it.name.lowercase() }
                }
                listItems.postValue(repoList)
            } catch (e: Throwable) {
                val intent = Intent(context, ErrorActivity::class.java)
                intent.putExtra("error", e.toString())
                context.startActivity(intent)
            }

        }
    }

    fun getSearch(): LiveData<ArrayList<Item>> {
        return listItems
    }
}