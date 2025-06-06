package no.advokat.identer.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import no.advokat.identer.R
import no.advokat.identer.ui.navigation.Screen
import no.advokat.identer.ui.theme.ForsvarerTheme

@Composable
fun SplashScreen(navController: NavController){

    LaunchedEffect(Unit) {
        delay(1500L)
        navController.navigate(Screen.Login.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }
    Box (
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo_image,),
            contentDescription = "Logo Image",
            modifier = Modifier.size(200.dp))
    }
}

@Preview("Drawer contents (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview(){

    ForsvarerTheme {
        SplashScreen(
            navController = rememberNavController()
        )
    }

}