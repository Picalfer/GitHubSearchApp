package com.landfathich.githubsearchapp.ui.content

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

    fun getContent(owner: String, repo: String) {

        CoroutineScope(Dispatchers.IO).launch {
            val contentResponse = RetrofitClient.apiInstance.getRepoContent(owner, repo)
            listFiles.postValue(contentResponse)
        }
    }

    fun getSearch(): LiveData<ArrayList<RepoContentItem>> {
        return listFiles
    }
}