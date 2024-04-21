package com.example.akhbaar

import kotlinx.coroutines.delay
import org.jsoup.Jsoup
import org.jsoup.parser.Parser
import org.jsoup.select.Elements
import org.junit.Test

import org.junit.Assert.*
import java.net.URL
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun getNewsFromFeedUrl() {
        runBlocking {
            emitNews("https://www.thehindu.com/feeder/default.rss").onEach { delay(1000L) }.collectLatest{
                println("News Title: $it")
            }
        }
    }

    private fun emitNews(rssUrl: String) : Flow<List<String>> {

        val rssUrl = URL(rssUrl)
        val doc: org.jsoup.nodes.Document =
            Jsoup.parse(rssUrl.openStream(), "UTF-8", "", Parser.xmlParser())
        val newsTitleList = mutableListOf<String>()
        var title: String?

        val itemElement: Elements = doc.select("item")
        for (item in itemElement) {
            title = item.allElements.select("title").text() ?: "<Empty tile>"
            newsTitleList.add(title)
        }

        return flowOf(newsTitleList)
    }
}