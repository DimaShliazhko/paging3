package com.example.paging3.prentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.paging3.common.AnalyticsLogger
import com.example.paging3.common.AnalyticsLoggerIml
import com.example.paging3.navigation.Navigation
import com.example.paging3.prentation.ui.theme.Paging3Theme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), AnalyticsLogger by AnalyticsLoggerIml() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registrationLifecycleOwner(this)
        setContent {
            Paging3Theme {
                val navController = rememberAnimatedNavController()
                Navigation(navController = navController)
            }
        }
    }
}