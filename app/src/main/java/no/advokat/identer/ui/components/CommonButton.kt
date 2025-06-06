package no.advokat.identer.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CommonButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Color(0xFFC1FF72), // Default background color
    contentColor: Color = Color.Black, // Default text color
    trailingIcon: @Composable (() -> Unit)? = null // Optional trailing icon
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(50.dp), // Adjust the corner radius as needed
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor, // Dynamic background color
            contentColor = contentColor // Dynamic text color
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = if (trailingIcon == null) Arrangement.Center else Arrangement.Center,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically // Ensures proper vertical alignment
        ) {
            Text(text = text)
            if (trailingIcon != null) {
                trailingIcon()
            }
        }
    }
}