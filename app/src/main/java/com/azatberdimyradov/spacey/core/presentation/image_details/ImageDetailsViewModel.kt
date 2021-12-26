package com.azatberdimyradov.spacey.core.presentation.image_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.azatberdimyradov.spacey.core.Constants
import com.azatberdimyradov.spacey.core.DownloadImage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageDetailsViewModel @Inject constructor(
    private val downloadImage: DownloadImage,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _imageUrlState = mutableStateOf("")
    val imageUrlState: State<String> = _imageUrlState

    private val _dateState = mutableStateOf("")
    val dateState: State<String> = _dateState

    init {
        savedStateHandle.get<String>(Constants.PARAM_IMAGE_URL)?.let { imageUrl ->
            _imageUrlState.value = imageUrl
        }
        savedStateHandle.get<String>(Constants.PARAM_DATE)?.let { date ->
            _dateState.value = date
        }
    }

    fun downloadImage(fileName: String, imageUrl: String) {
        downloadImage.download(fileName, imageUrl)
    }
}