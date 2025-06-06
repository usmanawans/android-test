package no.advokat.identer.ui.screen.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import no.advokat.identer.R
import no.advokat.identer.ui.theme.ForsvarerTheme
import no.advokat.identer.ui.theme.Primary
@Composable
fun HomeCardViewComponent(
    title: String,
    icon: Painter // Changed from Bitmap to Painter
)
{
    Column(
        modifier = Modifier.padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            // Apply color to the Card directly, not as a background modifier
            colors = CardDefaults.cardColors(
                containerColor = Primary
            ),
            modifier = Modifier.size(84.dp)
        ) {
            // Box is the key component for centering content
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center // This centers the content of the Box
            ) {
                Image(
                    painter = icon,
                    contentDescription = "Image of $title",
                    modifier = Modifier
                        .padding(16.dp)  // Add some padding so icon isn't flush against card edges
                        .fillMaxSize()   // Fill available space in the Box
                )
            }
        }
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary

        )
    }
}
@Preview("Drawer contents (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
private fun HomeCardPreview(){

    ForsvarerTheme {
        HomeCardViewComponent(
            title = "Hasee",
            icon = painterResource(id = R.drawable.ic_hus) // Use a drawable resource

        )
    }

}