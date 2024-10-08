package com.example.akhbaar.ui.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedsScreenScaffold(){
    Scaffold (
        topBar = {
            TopAppBar()
        },
        bottomBar = {
            BottomNavBar()
        }
    ){
        FeedsScreenComponent(it)
    }
}

@Composable
fun FeedsScreenComponent(paddingValues: PaddingValues){
    Column(Modifier.padding(paddingValues)) {
        Text(text = "Feeds Activity")
    }
}