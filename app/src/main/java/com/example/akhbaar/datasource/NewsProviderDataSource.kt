package com.example.akhbaar.datasource

import com.example.akhbaar.api.web.data.News
import com.example.akhbaar.api.web.NewsApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow

private const val REFRESH_DELAY = 100000L

class NewsProviderDataSource(
    private val newsApi: NewsApi = NewsApi(),
    private val newsFeedSource: List<String>
) {
    val getLatestNews: Flow<Set<News>> = flow {
        while (true) {
            newsFeedSource.forEach {
                val latestNews = newsApi.getNewsFromFeedUrl(it)
                emit(latestNews)
                delay(REFRESH_DELAY)
            }
        }
    }
}