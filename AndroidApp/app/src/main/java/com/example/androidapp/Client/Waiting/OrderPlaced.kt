package com.example.androidapp.Client.Waiting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun OrderPlaced() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.AccessTime,
            contentDescription = "Waiting Icon",
            tint = Color(0xFFCC0000),
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = "Zamówienie zaakceptowane! Kucharze już przygotowują Twoja potrawę.",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
fun OrderPlacedPreview() {
    OrderPlaced()
}
