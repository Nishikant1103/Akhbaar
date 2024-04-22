package com.example.akhbaar.ui.view.components

import android.content.pm.ApplicationInfo
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.example.akhbaar.api.web.data.News

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(latestNewsSet: Set<News>?, onNewsItemClick: (news: News) -> Unit) {
    val appInfo: ApplicationInfo = LocalContext.current.applicationInfo
    val appLabelResString: String = if (appInfo.labelRes == 0) {
        appInfo.nonLocalizedLabel.toString()
    } else {
        LocalContext.current.getString(appInfo.labelRes)
    }
//    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        appLabelResString
                    )
                }
            )
        }

    ) { innerPadding ->
        NewsListView(
            latestNewsSet = latestNewsSet,
            onNewsItemClick = {
                onNewsItemClick(it)
            },
            paddingValues = innerPadding
        )
    }
}

//
//@Composable
//fun AppBarTitle() {
//    val appInfo: ApplicationInfo = LocalContext.current.applicationInfo
//
//    val appLabelResString: String = if (appInfo.labelRes == 0) {
//        appInfo.nonLocalizedLabel.toString()
//    } else {
//        LocalContext.current.getString(appInfo.labelRes)
//    }
//
//    Text(
//        text = appLabelResString,
//        style = MaterialTheme.typography.titleLarge,
//        modifier = Modifier.fillMaxWidth(),
//        textAlign = TextAlign.Center
//    )
//}