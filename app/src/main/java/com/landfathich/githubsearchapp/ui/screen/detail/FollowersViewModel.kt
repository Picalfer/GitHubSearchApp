package com.landfathich.githubsearchapp.ui.screen.detail

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.landfathich.githubsearchapp.data.api.RetrofitClient
import com.landfathich.githubsearchapp.data.model.User
import com.landfathich.githubsearchapp.ui.screen.ErrorActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FollowersViewModel: ViewModel() {
    val listFollowers = MutableLiveData<ArrayList<User>>()

    fun setListFollowers(context: Context, username: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userList = RetrofitClient.apiInstance.getFollowers(username)
                listFollowers.postValue(userList.body())
            } catch (e: Throwable) {
                val intent = Intent(context, ErrorActivity::class.java)
                intent.putExtra("error", e.toString())
                context.startActivity(intent)
            }
        }
    }

    fun getListFollowers(): LiveData<ArrayList<User>> {
        return listFollowers
    }
}