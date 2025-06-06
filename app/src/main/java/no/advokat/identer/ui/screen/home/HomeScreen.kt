package no.advokat.identer.ui.screen.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import no.advokat.identer.R
import no.advokat.identer.ui.screen.component.HomeCardViewComponent
import no.advokat.identer.ui.screen.component.TopAppBarComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
){
    val cases = listOf("Familie", "Barn", "Hus", "Skade", "Nabo" , "Bil", "Vold" , "Innkasso", "Straff",
        "Avtale", "Arv", "Annet" )

    Scaffold(
        // top app bar
        topBar = {
            TopAppBarComponent("Advokat")
        },


        // main surface
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
            ) {

                Text(text = "What's Happening with Case?",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().padding(10.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                ) {
                    items(cases) { item ->
                        HomeCardViewComponent(
                            title = item,
                            icon = painterResource(
                                id = when(item) {
                                    "Familie" -> R.drawable.ic_familie
                                    "Barn" -> R.drawable.ic_barn
                                    "Hus" -> R.drawable.ic_hus
                                    "Skade" -> R.drawable.ic_skade
                                    "Nabo" -> R.drawable.ic_nabo
                                    "Bil" -> R.drawable.ic_bil
                                    "Vold" -> R.drawable.ic_vold
                                    "Innkasso" -> R.drawable.ic_innkasso
                                    "Straff" -> R.drawable.ic_straff
                                    "Avtale" -> R.drawable.ic_avtale
                                    "Arv" -> R.drawable.ic_arv
                                    "Annet" -> R.drawable.ic_annet

                                    else -> R.drawable.logo_image
                                }
                            )
                        )
                        // REMOVE THE EXTRA CLOSING BRACE THAT WAS HERE
                    }
                }
            }


        },

        // bottom app bar
        bottomBar = {
            BottomAppBar {
                Text(
                    text = "Bottom Bar",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    )


}



@Preview("Drawer contents (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    HomeScreen(
        navController = rememberNavController()
    )
}