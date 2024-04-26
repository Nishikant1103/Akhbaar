package com.example.akhbaar.ui.view.components

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.akhbaar.R
import com.example.akhbaar.api.web.data.News
import com.example.akhbaar.ui.theme.AkhbaarTheme
import com.example.akhbaar.ui.theme.LocalAppDimensions

@Composable
fun NewsDetailScreen(news: News, paddingValues: PaddingValues) {
    Card(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        val context = LocalContext.current
        Text(
            text = news.newsTitle,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalAppDimensions.dimenMedium),
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = FontFamily.SansSerif
        )

        val newsDescription =
            if (news.newsDescription == "") "<--Empty Description-->" else news.newsDescription

        Text(
            text = newsDescription,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalAppDimensions.dimenLarge),
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = FontFamily.SansSerif
        )
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            Icon(
                painter = painterResource(id = R.drawable.open_news_article_in_browser),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(LocalAppDimensions.dimenBig + LocalAppDimensions.dimenLarge)
                    .align(Alignment.CenterHorizontally)
                    .clickable(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.newsLink))
                            context.startActivity(intent)
                        }
                    )
            )
            Text(
                text = "Go to the Article",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalAppDimensions.dimenTiny),
                textAlign = TextAlign.Center
            )
            Text(
                text = news.datePosted,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalAppDimensions.dimenLarge),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
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
fun NewsDetailedViewPreview() {
    val dummyNews = News(
        newsTitle = "News 1",
        newsDescription = "News 1 description",
        newsLink = "abc@gmail.com"
    )

    AkhbaarTheme {
        NewsDetailScreen(dummyNews, paddingValues = PaddingValues(4.dp))
    }
}