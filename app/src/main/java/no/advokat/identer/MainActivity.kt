package no.advokat.identer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import no.advokat.identer.ui.navigation.NavigationStack
import no.advokat.identer.ui.theme.ForsvarerTheme

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ForsvarerTheme {
                NavigationStack()
            }
        }
    }
}