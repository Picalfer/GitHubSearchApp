package com.landfathich.githubsearchapp.ui.content

import androidx.lifecycle.ViewModel
import com.landfathich.githubsearchapp.data.api.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContentViewModel : ViewModel() {

    fun getContent(owner: String, repo: String) {

        CoroutineScope(Dispatchers.IO).launch {
            val contentResponse = RetrofitClient.apiInstance.getRepoContent(owner, repo)

        }
    }
}