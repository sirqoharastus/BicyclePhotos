package com.abdulqohar.bicyclephotos.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.abdulqohar.bicyclephotos.R

@Composable
fun TextWithStartIcon(text: String, icon: ImageVector, modifier: Modifier, isVisible: Boolean = false) {
    Row(modifier = modifier) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Red
        )
        Text(
            text = text,
            fontFamily =  FontFamily(
                Font(R.font.poppins_medium, weight = FontWeight.Normal, style = FontStyle.Normal)
            )
        )
    }
}