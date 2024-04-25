package com.example.akhbaar.ui.view

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.akhbaar.api.web.data.News
import com.example.akhbaar.ui.theme.AkhbaarTheme
import com.example.akhbaar.ui.view.components.NewsDetailScreen

class NewsDetailedViewActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val news: News? = intent.getParcelableExtra(NEWS_ITEM_KEY, News::class.java)

        setContent {
            AkhbaarTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (news != null) {
                        NewsDetailScreen(news)
                    }
                }

            }
        }
    }
}

