package com.example.akhbaar.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.akhbaar.ui.theme.AkhbaarTheme
import com.example.akhbaar.ui.view.components.HomeScreenScaffold
import com.example.akhbaar.viewmodel.NewsVM

const val NEWS_ITEM_KEY = "news_item_key"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, NewsDetailedViewActivity::class.java)

        setContent {
            val newsVM: NewsVM = viewModel()
            val latestNewsSet = newsVM.getNewsListLD.observeAsState().value
            AkhbaarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreenScaffold(latestNewsSet, onNewsItemClick = {
                        intent.putExtra(NEWS_ITEM_KEY, it)
                        startActivity(intent)
                    })
                }
            }
        }
    }
}


