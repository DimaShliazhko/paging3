package com.example.paging3.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import com.example.paging3.prentation.screens.HomeScreen
import com.example.paging3.prentation.screens.SearchScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagingApi::class)
@Composable
fun Navigation(
    navController: NavHostController
) {
    AnimatedNavHost(navController = navController, startDestination = Screen.Home.route) {

        composable(
            route = Screen.Home.route,
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
            },
        ) {
            HomeScreen(navController = navController)
        }

        composable(
            route = Screen.Search.route,
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
            },
        ) {
            SearchScreen(navController = navController)
        }
    }
}