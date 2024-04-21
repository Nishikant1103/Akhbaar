package com.example.akhbaar.api.web

import com.example.akhbaar.api.web.data.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.parser.Parser
import org.jsoup.select.Elements
import java.net.URL


class NewsApi {

    fun getNewsFromFeedUrl(rssFeedUrl: String): Set<News> {
        val newsSet = mutableSetOf<News>()
        val rssUrl = URL(rssFeedUrl)
        val document: Document =
            Jsoup.parse(rssUrl.openStream(), "UTF-8", "", Parser.xmlParser())
        val itemElement: Elements = document.select("item")

        for (item in itemElement) {
            newsSet.add(getNews(item))
        }

        return newsSet
    }

    private fun getNews(element: Element): News {
        return News(
            datePosted = element.allElements.select("pubDate").text(),
            newsTitle = element.allElements.select("title").text(),
            newsDescription = element.allElements.select("description").text(),
            newsLink = element.allElements.select("link").text()
        )
    }
}
