package com.landfathich.githubsearchapp.ui.screen.content

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.landfathich.githubsearchapp.api.RetrofitClient
import com.landfathich.githubsearchapp.data.model.RepoContentItem
import com.landfathich.githubsearchapp.ui.screen.ErrorActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContentViewModel : ViewModel() {

    val listFiles = MutableLiveData<ArrayList<RepoContentItem>>()

    fun getContent(context: Context, owner: String, repo: String, path: String = "") {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val contentResponse = RetrofitClient.apiInstance.getRepoContent(owner, repo, path)
                listFiles.postValue(contentResponse.body())
            } catch (e: Throwable) {
                val intent = Intent(context, ErrorActivity::class.java)
                intent.putExtra("error", e.toString())
                context.startActivity(intent)
            }
        }
    }

    fun getSearch(): LiveData<ArrayList<RepoContentItem>> {
        return listFiles
    }
}