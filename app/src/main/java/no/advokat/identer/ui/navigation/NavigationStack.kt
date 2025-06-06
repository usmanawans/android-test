package no.advokat.identer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import no.advokat.identer.ui.screen.SplashScreen
import no.advokat.identer.ui.screen.home.HomeScreen
import no.advokat.identer.ui.screen.login.LoginScreen

@Composable
fun NavigationStack() {
    val navController =  rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash.route){
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
    }
}