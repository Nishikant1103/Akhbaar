package com.example.akhbaar.ui.view.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.akhbaar.api.web.data.News

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenScaffold(latestNewsSet: Set<News>?, onNewsItemClick: (news: News) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar()
        }

    ) { innerPadding ->
        NewsListScreen(
            latestNewsSet = latestNewsSet,
            onNewsItemClick = {
                onNewsItemClick(it)
            },
            paddingValues = innerPadding
        )
    }
}