package com.landfathich.githubsearchapp.ui.screen.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.landfathich.githubsearchapp.data.api.RetrofitClient
import com.landfathich.githubsearchapp.data.model.RepoContentItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContentViewModel : ViewModel() {

    val listFiles = MutableLiveData<ArrayList<RepoContentItem>>()

    fun getContent(owner: String, repo: String, path: String = "") {

        CoroutineScope(Dispatchers.IO).launch {
            val contentResponse = RetrofitClient.apiInstance.getRepoContent(owner, repo, path)
            listFiles.postValue(contentResponse.body())
        }
    }

    fun getSearch(): LiveData<ArrayList<RepoContentItem>> {
        return listFiles
    }
}