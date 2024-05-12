package com.example.androidapp.Client.MenuItem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.androidapp.ui.theme.AndroidAppTheme

@Composable
fun VideoPlayer(videoUrl: String) {
    val context = LocalContext.current
    val mediaItem = MediaItem.Builder()
        .setUri(videoUrl)
        .setMimeType(MimeTypes.APPLICATION_MP4).build()

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build()
    }

    exoPlayer.setMediaItem(mediaItem)

    val isPlaying = exoPlayer.playbackState > 0

    AndroidView(
        factory = { c ->
            PlayerView(c).apply {
                player = exoPlayer
            }
        },
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background)
    )

    IconButton(
        onClick = {
            if (isPlaying) {
                exoPlayer.pause()
            } else {
                exoPlayer.play()
            }
        },
        modifier = Modifier
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = if (isPlaying) "Pause" else "Play",
            tint = Color.White,
            modifier = Modifier.size(48.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VideoPlayerPreview() {
    AndroidAppTheme {
        VideoPlayer(videoUrl = "https://example.com/video.mp4")
    }
}