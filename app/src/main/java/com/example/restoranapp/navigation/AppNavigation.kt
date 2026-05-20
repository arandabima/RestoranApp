package com.example.restoranapp.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.restoranapp.data.PreferencesManager
import com.example.restoranapp.screens.DetailMenuScreen
import com.example.restoranapp.screens.EditProfileScreen
import com.example.restoranapp.screens.HomeScreen
import com.example.restoranapp.screens.MenuScreen
import com.example.restoranapp.screens.ProfileScreen
import com.example.restoranapp.screens.SplashScreen

object Routes {
    const val HOME = "home"
    const val MENU = "menu"
    const val DETAIL_MENU = "detail_menu/{menuId}"
    const val PROFILE = "profile"
    const val EDIT_PROFILE = "edit_profile"

    fun detailMenu(id: Int) = "detail_menu/$id"
}

@Composable
fun AppNavigation(darkMode: Boolean, onToggleDarkMode: () -> Unit) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val prefsManager = remember { PreferencesManager(context) }

    var showSplash by remember { mutableStateOf(true) }

    if (showSplash) {
        SplashScreen(onSplashFinished = { showSplash = false })
        return
    }

    Scaffold(
        bottomBar = { BottomNavBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.HOME,
                enterTransition = { fadeIn(animationSpec = tween(400)) },
                exitTransition = { fadeOut(animationSpec = tween(400)) }
            ) {
                HomeScreen(
                    prefsManager = prefsManager,
                    onNavigateToMenu = { navController.navigate(Routes.MENU) },
                    onNavigateToProfile = { navController.navigate(Routes.PROFILE) },
                    darkMode = darkMode,
                    onToggleDarkMode = onToggleDarkMode
                )
            }
            composable(Routes.MENU,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(400)
                    )
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(400)
                    )
                },
                popEnterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { -it },
                        animationSpec = tween(400)
                    )
                },
                popExitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { it },
                        animationSpec = tween(400)
                    )
                }
            ) {
                MenuScreen(
                    onItemClick = { id -> navController.navigate(Routes.detailMenu(id)) },
                    onBack = { navController.popBackStack() }
                )
            }
            composable(Routes.DETAIL_MENU,
                arguments = listOf(navArgument("menuId") { type = NavType.IntType }),
                enterTransition = {
                    slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(400)
                    )
                },
                exitTransition = {
                    slideOutVertically(
                        targetOffsetY = { it },
                        animationSpec = tween(400)
                    )
                }
            ) { backStackEntry ->
                val menuId = backStackEntry.arguments?.getInt("menuId") ?: 1
                DetailMenuScreen(menuId = menuId, onBack = { navController.popBackStack() })
            }
            composable(Routes.PROFILE,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { -it },
                        animationSpec = tween(400)
                    )
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { it },
                        animationSpec = tween(400)
                    )
                },
                popEnterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(400)
                    )
                },
                popExitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(400)
                    )
                }
            ) {
                ProfileScreen(
                    prefsManager = prefsManager,
                    onNavigateToEdit = { navController.navigate(Routes.EDIT_PROFILE) },
                    onBack = { navController.popBackStack() }
                )
            }
            composable(Routes.EDIT_PROFILE,
                enterTransition = {
                    slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(400)
                    )
                },
                exitTransition = {
                    slideOutVertically(
                        targetOffsetY = { it },
                        animationSpec = tween(400)
                    )
                }
            ) {
                EditProfileScreen(
                    prefsManager = prefsManager,
                    onSave = { navController.popBackStack() },
                    onCancel = { navController.popBackStack() }
                )
            }
        }
    }
}