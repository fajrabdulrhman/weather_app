package com.example.instgram_profile.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import io.ktor.client.request.invoke

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,

    )

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
val CustomMediumText = TextStyle(
    fontWeight = FontWeight.Medium,   // Weight 500
    fontSize = 64.sp,                 // Size 64px
    lineHeight = 29.71.sp,            // Line height
    letterSpacing = 0.47.sp,          // Letter spacing
    textAlign = TextAlign.Center      // Horizontal alignment Center
)
val PoppinsLikeText = TextStyle(
    fontFamily = FontFamily.SansSerif, // Roboto by default
    fontWeight = FontWeight.Medium,    // زي 500
    fontSize = 20.sp,
    lineHeight = 29.71.sp,
    letterSpacing = 0.47.sp
)
