package no.advokat.identer.ui.screen.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import no.advokat.identer.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent(
    title: String
){

    CenterAlignedTopAppBar(
        title = { Text(title , style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp)) },
        colors = TopAppBarDefaults.topAppBarColors(Primary),
    )

}