package com.example.akhbaar.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.akhbaar.api.web.data.News
import com.example.akhbaar.repository.NewsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.toSet
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsVM(private val newsRepo: NewsRepo = NewsRepo()) : ViewModel() {

    private val _getLatestNews = MutableLiveData<Set<News>>().apply {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepo.getLatestNews.collect {
              postValue(it)
            }
        }
    }


    val getNewsListLD: LiveData<Set<News>> = _getLatestNews

}