package com.example.akhbaar.ui.view.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.akhbaar.api.web.data.News
import com.example.akhbaar.ui.theme.AkhbaarTheme
import com.example.akhbaar.ui.theme.LocalAppDimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(latestNewsSet: Set<News>?, onNewsItemClick: (news: News) -> Unit, paddingValues: PaddingValues) {
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState , modifier = Modifier.padding(paddingValues)) {
        if (latestNewsSet != null) {
            itemsIndexed(latestNewsSet.toList(), key = { _, news -> news.hashCode() }) { _, news ->
                Card(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(
                            horizontal = LocalAppDimensions.dimenLarge,
                            vertical = LocalAppDimensions.dimenMedium
                        )
                        .clickable(
                            onClick = { onNewsItemClick(news) }
                        )
                ) {
                    Text(
                        text = news.newsTitle,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(LocalAppDimensions.dimenLarge),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontFamily = FontFamily.SansSerif
                    )
                    Text(
                        text = news.datePosted,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(LocalAppDimensions.dimenLarge),
                        textAlign = TextAlign.Right,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                }
            }
        }
    }
}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun ReplyAppPreview() {
    val dummyNews = mutableSetOf(
        News(newsTitle = "News 1", newsDescription = "News 1 description"),
        News(newsTitle = "News 2", newsDescription = "News 2 description")
    )
    AkhbaarTheme {
        NewsListScreen(dummyNews, onNewsItemClick = {}, PaddingValues(4.dp))
    }
}