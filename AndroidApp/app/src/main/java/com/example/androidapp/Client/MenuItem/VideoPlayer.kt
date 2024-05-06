package com.example.androidapp.Client.MenuItem

import android.content.Context
import android.widget.VideoView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun VideoPlayer(videoUrl: String) {
    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = { context: Context ->
            VideoView(context).apply {
                setVideoPath(videoUrl)
                start()
            }
        }
    )
}