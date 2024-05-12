package com.example.androidapp.Utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.androidapp.R

@Composable
fun SatisfyText(text: String,
                modifier: Modifier = Modifier,
                textAlign: TextAlign = TextAlign.Start,
                fontSize: TextUnit = 18.sp,
                color: Color = MaterialTheme.colorScheme.onBackground
) {
    val satisfy = FontFamily(Font(R.font.satisfy_regular, FontWeight.Normal))

    Text(
        text = text,
        modifier = modifier,
        textAlign = textAlign,
        fontSize = fontSize,
        fontFamily = satisfy,
        color = color
    )
}