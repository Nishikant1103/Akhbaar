package com.example.akhbaar.repository

import com.example.akhbaar.api.web.data.News
import com.example.akhbaar.datasource.NewsProviderDataSource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toSet

class NewsRepo(
    private val newsProviderDataSource: NewsProviderDataSource = NewsProviderDataSource(
        newsFeedSource = listOf("https://www.thehindu.com/feeder/default.rss")
    )
) {
    val getLatestNews: Flow<Set<News>> = newsProviderDataSource.getLatestNews.map { news ->
        news.filter {
            it.newsTitle != ""
        }.toSet()
    }
}