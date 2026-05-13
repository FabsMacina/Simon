package com.example.simon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import android.net.Uri
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.simon.ui.theme.SimonTheme

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        //Enables edge-to-edge display on API level < 35
        enableEdgeToEdge()

        //Allows for the layout to be displayed
        setContent {
            SimonTheme {

                //Creates a nav controller in order to switch screens
                val navController = rememberNavController()

                //Creates a GameHistory object that will persist until the app is closed.
                //Extending ViewModel allows the instance to be saved on HistoryScreen
                val currentHistory: GameHistory = viewModel()

                Scaffold(modifier = Modifier.fillMaxSize()){ innerPadding ->
                    NavHost(
                        navController = navController, startDestination = "history",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("main"){

                            //The GameHistory object is passed to the MainScreen
                            MainScreen( history = currentHistory,
                                onEndClicked = { navController.navigate("history") })
                        }

                        composable("history"){

                            //The GameHistory object is passed to the HistoryScreen
                            HistoryScreen(currentHistory,
                                onGameClicked = { navController.navigate("main") },
                                onDetailClicked = { sequence, count ->
                                    val encoded = Uri.encode(sequence)
                                    navController.navigate("detail/$encoded/$count") })
                        }

                        composable("detail/{sequence}/{count}",
                            listOf(
                            navArgument("sequence") { type = NavType.StringType },
                            navArgument("count") { type = NavType.IntType }))
                        {   backStackEntry ->
                                val sequence = backStackEntry.arguments?.getString("sequence") ?: ""
                                val count = backStackEntry.arguments?.getInt("count") ?: 0
                                MatchDetailScreen(sequence = sequence, count = count)
                        }
                    }
                }
            }
        }
    }
}
