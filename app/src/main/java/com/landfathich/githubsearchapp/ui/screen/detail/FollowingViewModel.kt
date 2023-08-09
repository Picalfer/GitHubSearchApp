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

class FollowingViewModel: ViewModel() {
    val listFollowing = MutableLiveData<ArrayList<User>>()

    fun setListFollowing(context: Context, username: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userList = RetrofitClient.apiInstance.getFollowing(username)
                listFollowing.postValue(userList.body())
            } catch (e: Throwable) {
                val intent = Intent(context, ErrorActivity::class.java)
                intent.putExtra("error", e.toString())
                context.startActivity(intent)
            }
        }
    }

    fun getListFollowing(): LiveData<ArrayList<User>> {
        return listFollowing
    }
}