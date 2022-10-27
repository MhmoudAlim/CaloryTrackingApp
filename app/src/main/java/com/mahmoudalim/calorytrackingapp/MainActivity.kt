package com.mahmoudalim.calorytrackingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mahmoudalim.calorytrackingapp.navigation.navigate
import com.mahmoudalim.calorytrackingapp.ui.theme.CaloryTrackingAppTheme
import com.mahmoudalim.core.navigation.Route
import com.mahmoudalim.onboarding_presentation.activity_level.ActivityLevelScreen
import com.mahmoudalim.onboarding_presentation.age.AgeScreen
import com.mahmoudalim.onboarding_presentation.gender.GenderScreen
import com.mahmoudalim.onboarding_presentation.goal.GoalScreen
import com.mahmoudalim.onboarding_presentation.height.HeightScreen
import com.mahmoudalim.onboarding_presentation.nutrient_goal.NutrientGoalScreen
import com.mahmoudalim.onboarding_presentation.weight.WeightScreen
import com.mahmoudalim.onboarding_presentation.welcome.WelcomeScreen
import com.mahmoudalim.tracker_presentation.screens.overview.OverViewScreen
import com.mahmoudalim.tracker_presentation.screens.search.SearchScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackingAppTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.WELCOME
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.AGE) {
                            AgeScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.GENDER) {
                            GenderScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.HEIGHT) {
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.WEIGHT) {
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.ACTIVITY) {
                            ActivityLevelScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.GOAL) {
                            GoalScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.NUTRIENT_GOAL) {
                            NutrientGoalScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.TRACKER_OVERVIEW) {
                            OverViewScreen(onNavigate = navController::navigate)
                        }
                        composable(
                            route = Route.SEARCH + "/{mealName}/{dayOfMonth}/{month}/{year}",
                            arguments = listOf(
                                navArgument("mealName") { type = NavType.StringType },
                                navArgument("dayOfMonth") { type = NavType.IntType },
                                navArgument("month") { type = NavType.IntType },
                                navArgument("year") { type = NavType.IntType }
                            )
                        ) {
                            val mealName = it.arguments?.getString("mealName") ?: ""
                            val day = it.arguments?.getInt("dayOfMonth") ?: 0
                            val month = it.arguments?.getInt("month") ?: 0
                            val year = it.arguments?.getInt("year") ?: 0
                            SearchScreen(
                                scaffoldState = scaffoldState,
                                mealName = mealName,
                                dayOfMonth = day,
                                month = month,
                                year = year,
                                onNavigateUp = { navController.navigateUp() }
                            )
                        }
                    }
                }

            }
        }
    }
}
