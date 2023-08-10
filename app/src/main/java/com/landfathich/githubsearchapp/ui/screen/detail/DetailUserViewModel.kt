package com.landfathich.githubsearchapp.ui.screen.detail

import android.app.Application
import android.content.Context
import android.content.Intent
import android.service.autofill.UserData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.landfathich.githubsearchapp.api.RetrofitClient
import com.landfathich.githubsearchapp.data.local.FavoriteUser
import com.landfathich.githubsearchapp.data.local.FavoriteUserDao
import com.landfathich.githubsearchapp.data.local.UserDatabase
import com.landfathich.githubsearchapp.data.model.DetailUserResponse
import com.landfathich.githubsearchapp.ui.screen.ErrorActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailUserViewModel(application: Application) : AndroidViewModel(application) {
    val user = MutableLiveData<DetailUserResponse>()

    private var userDao: FavoriteUserDao?
    private var userDb: UserDatabase?

    init {
        userDb = UserDatabase.getDatabase(application)
        userDao = userDb?.favoriteUserDao()
    }

    fun setUserDetail(context: Context, username: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userResponse = RetrofitClient.apiInstance
                    .getUserDetail(username)
                user.postValue(userResponse.body())
            } catch (e: Throwable) {
                val intent = Intent(context, ErrorActivity::class.java)
                intent.putExtra("error", e.toString())
                context.startActivity(intent)
            }
        }
    }

    fun getUserDetail(): LiveData<DetailUserResponse> {
        return user
    }

    fun addToFavorite(username: String, id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            var user = FavoriteUser(
                username,
                id
            )

            userDao?.addToFavorite(user)
        }
    }

    suspend fun checkUser(id: Int) = userDao?.checkUser(id)

    fun removeFromFavorite(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.removeFromFavorite(id)
        }
    }
}