package no.advokat.identer.ui.screen.login

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import no.advokat.identer.ui.theme.ForsvarerTheme
import no.advokat.identer.ui.components.CommonButton
import androidx.compose.ui.graphics.Color
import no.advokat.identer.R
import no.advokat.identer.ui.navigation.Screen


@Composable
fun LoginScreen(navController: NavController){

    Box (
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column (
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(R.drawable.logo_image,),
                contentDescription = "Logo Image",
                modifier = Modifier.size(100.dp))
            Text(
                "Get in Touch with your Advocate",
                modifier = Modifier.padding(top = 16.dp), // Add top margin
                style = MaterialTheme.typography.titleMedium, // Using your custom typography
                color = MaterialTheme.colorScheme.onBackground // Using your custom color directly
            )
            CommonButton(
                text = "Login with ",
                onClick = {}, // Trigger Vipps login
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                containerColor = Color(0xFFFF5B24),
                contentColor = Color.White,
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.vipps_btn_logo),
                        contentDescription = "Vipps Logo",
                        tint = Color.White
                    )
                }
            )

            CommonButton(
                text = "Login Test",
                onClick = {navController.navigate(Screen.Home.route)},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                containerColor = Color.Black,
                contentColor = Color.White
            )
        }

    }

}

@Preview(showBackground = true)
@Preview("Drawer contents (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoginScreenPreview(){
    ForsvarerTheme {
        LoginScreen(
            navController = rememberNavController()
        )
    }
}