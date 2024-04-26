package com.example.akhbaar.ui.view.components

import android.content.pm.ApplicationInfo
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.akhbaar.ui.theme.Dimensions
import com.example.akhbaar.ui.theme.LocalAppDimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    val appInfo: ApplicationInfo = LocalContext.current.applicationInfo
    val appLabelResString: String = if (appInfo.labelRes == 0) {
        appInfo.nonLocalizedLabel.toString()
    } else {
        LocalContext.current.getString(appInfo.labelRes)
    }

    CenterAlignedTopAppBar(
        modifier = Modifier.padding(bottom = LocalAppDimensions.dimenMedium),
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