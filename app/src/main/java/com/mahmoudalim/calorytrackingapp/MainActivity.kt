package com.mahmoudalim.calorytrackingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mahmoudalim.calorytrackingapp.ui.theme.CaloryTrackingAppTheme
import com.mahmoudalim.core.domian.preferences.Preferences
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
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

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
                        startDestination = if (preferences.loadShouldShowOnboarding()) Route.WELCOME else Route.TRACKER_OVERVIEW
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(
                                onNextClick = { navController.navigate(Route.GENDER) }
                            )

                        }
                        composable(Route.GENDER) {
                            GenderScreen(
                                onNextClick = { navController.navigate(Route.AGE) }
                            )
                        }
                        composable(Route.AGE) {
                            AgeScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = { navController.navigate(Route.HEIGHT) }
                            )
                        }
                        composable(Route.HEIGHT) {
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = { navController.navigate(Route.WEIGHT) }
                            )
                        }
                        composable(Route.WEIGHT) {
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = { navController.navigate(Route.ACTIVITY) }
                            )
                        }
                        composable(Route.ACTIVITY) {
                            ActivityLevelScreen(
                                onNextClick = { navController.navigate(Route.GOAL) }
                            )
                        }
                        composable(Route.GOAL) {
                            GoalScreen(
                                onNextClick = { navController.navigate(Route.NUTRIENT_GOAL) }
                            )
                        }
                        composable(Route.NUTRIENT_GOAL) {
                            NutrientGoalScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = { navController.navigate(Route.TRACKER_OVERVIEW) }
                            )
                        }
                        composable(Route.TRACKER_OVERVIEW) {
                            OverViewScreen(
                                onNextClick = { mealName, day, month, year ->
                                    navController.navigate(
                                        Route.SEARCH +
                                                "/$mealName" +
                                                "/$day" +
                                                "/$month" +
                                                "/$year"
                                    )
                                }
                            )
                        }
                        composable(
                            route =
                            Route.SEARCH + "/{mealName}/{dayOfMonth}/{month}/{year}",
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
