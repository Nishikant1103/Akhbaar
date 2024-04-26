package com.example.akhbaar.ui.view.components

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.akhbaar.R
import com.example.akhbaar.ui.data.BottomNavigationItem
import com.example.akhbaar.ui.theme.LocalAppDimensions
import com.example.akhbaar.ui.view.FeedsActivity
import com.example.akhbaar.ui.view.MainActivity

private val bottomNavItems = listOf(
    BottomNavigationItem(
        position = 0,
        iconUnselected = R.drawable.ic_home_outlined,
        iconSelected = R.drawable.ic_home_filled,
        label = "Home",
        activityName = MainActivity::class.java.name
    ),
    BottomNavigationItem(
        position = 1,
        iconUnselected = R.drawable.ic_feed_outlined,
        iconSelected = R.drawable.ic_feed_filled,
        label = "Feeds",
        activityName = FeedsActivity::class.java.name
    ),
)

@Composable
fun BottomNavBar() {
    BottomNavigation(
        modifier = Modifier.padding(top = LocalAppDimensions.dimenMedium),
        backgroundColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        val currentSelection = rememberSaveable { mutableStateOf(0) }
        val context = LocalContext.current

        bottomNavItems.forEach {
            val icon =
                if (currentSelection.value == it.position) it.iconSelected else it.iconUnselected
            BottomNavigationItem(
                selected = currentSelection.value == it.position,
                icon = {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                onClick = {
                    setAppModule(it.position, context)
                    currentSelection.value = it.position
                },
            )
        }

    }
}

fun setAppModule(nextModuleIndex: Int, context: Context) {
    val nextActivity = bottomNavItems.singleOrNull {
        it.position == nextModuleIndex
    }?.activityName

    val intent = Intent(context, nextActivity?.let { Class.forName(it) })
    context.startActivity(intent)

}

private fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}
