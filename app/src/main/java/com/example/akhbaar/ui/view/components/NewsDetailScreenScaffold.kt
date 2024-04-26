package com.example.akhbaar.ui.view.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.akhbaar.api.web.data.News

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreenScaffold(news: News){
    Scaffold (
        topBar = {
            TopAppBar()
        },
        bottomBar = {
            BottomNavBar()
        }
    ){
        NewsDetailScreen(news = news, paddingValues = it)
    }
}