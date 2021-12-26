package com.azatberdimyradov.spacey.feature_astronomy_pictures.presentation.astronomy_pictures

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.azatberdimyradov.spacey.feature_astronomy_pictures.domain.model.AstronomyPictures
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun AstronomyPicturesItem(
    astronomyPictures: AstronomyPictures,
    onItemClick: (AstronomyPictures) -> Unit
) {
    val activity = LocalContext.current as AppCompatActivity
    Column(modifier = Modifier.fillMaxWidth()) {
        if (astronomyPictures.media_type == "video") {
            //video
            AndroidView(
                factory = { YouTubePlayerView(it) },
                modifier = Modifier.fillMaxWidth()
            ) { youTubePlayerView ->
                activity.lifecycle.addObserver(youTubePlayerView)
                youTubePlayerView.addYouTubePlayerListener(
                    object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            val videoUrl = astronomyPictures.url.split("https://www.youtube.com/embed/","?")
                            youTubePlayer.cueVideo(videoId = videoUrl[1], 0f)
                        }
                    }
                )
            }
        } else {
            //image
            GlideImage(
                imageModel = astronomyPictures.url,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .clickable {
                        onItemClick(astronomyPictures)
                    },
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                },
                failure = {
                    Text(text = "Image request failed", color = Color.White)
                }
            )
        }
        Text(
            text = astronomyPictures.title,
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        ExpandableText(
            text = astronomyPictures.explanation,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        Text(
            text = astronomyPictures.date,
            color = Color.White,
            fontSize = 15.sp,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    }
}

private const val MINIMIZED_MAX_LINES = 2

@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    text: String
) {
    var isExpanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    var isClickable by remember { mutableStateOf(false) }
    var finalText by remember { mutableStateOf(text) }
    val textLayoutResult = textLayoutResultState.value

    LaunchedEffect(
        textLayoutResult
    ) {
        if (textLayoutResult == null) {
            return@LaunchedEffect
        }
        when {
            isExpanded -> {
                finalText = "$text Show Less"
            }
            !isExpanded && textLayoutResult.hasVisualOverflow -> {
                val lastCharIndex = textLayoutResult.getLineEnd(MINIMIZED_MAX_LINES - 1)
                val showMoreString = "...Show More"
                val adjustedText = text
                    .substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(showMoreString.length)
                    .dropLastWhile {
                        it == ' ' || it == '.'
                    }
                finalText = "$adjustedText$showMoreString"
                isClickable = true
            }
        }
    }
    Text(
        text = finalText,
        maxLines = if (isExpanded) Int.MAX_VALUE else MINIMIZED_MAX_LINES,
        onTextLayout = {
            textLayoutResultState.value = it
        },
        modifier = modifier
            .clickable(enabled = isClickable) {
                isExpanded = !isExpanded
            }
            .animateContentSize(),
        fontSize = 15.sp,
        color = Color.White
    )
}