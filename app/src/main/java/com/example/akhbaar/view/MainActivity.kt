package com.example.akhbaar.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.akhbaar.api.web.data.News
import com.example.akhbaar.ui.theme.AkhbaarTheme
import com.example.akhbaar.viewmodel.NewsVM

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AkhbaarTheme {
                val newsVM: NewsVM = viewModel()
                val latestNewsSet = newsVM.getNewsListLD.observeAsState().value
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewsListView(latestNewsSet)
                }
            }
        }
    }
}

@Composable
fun NewsListView(latestNewsSet: Set<News>?) {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        if (latestNewsSet != null) {
            itemsIndexed(latestNewsSet.toList(), key = { _, news -> news.hashCode() }) { _, news ->
                Surface(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(10.dp)
                        .background(color = Color.Red, shape = MaterialTheme.shapes.medium)
                ) {
                    Column {
                        Text(
                            text = news.newsTitle,
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(2.dp)
                        )
                        Text(
                            text = news.newsDescription,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(2.dp)
                        )
                    }
                }
            }
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    AkhbaarTheme {
//        NewsListView(mutableSetOf())
//    }
//}