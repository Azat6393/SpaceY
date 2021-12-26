package com.azatberdimyradov.spacey.feature_astronomy_pictures.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azatberdimyradov.spacey.core.Resource
import com.azatberdimyradov.spacey.core.dateOnInit
import com.azatberdimyradov.spacey.feature_astronomy_pictures.domain.use_case.GetAstronomyPictures
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@SuppressLint("SimpleDateFormat")
@HiltViewModel
class AstronomyPicturesViewModel @Inject constructor(
    private val getAstronomyPictures: GetAstronomyPictures
) : ViewModel() {

    private val _state = mutableStateOf(AstronomyPicturesState())
    val state: State<AstronomyPicturesState> = _state

    init {
        val date = dateOnInit()
        searchAstronomyPictures(date.first, date.second)
    }

    fun searchAstronomyPictures(start_date: String, end_date: String) {
        getAstronomyPictures(
            start_date = start_date,
            end_date = end_date
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AstronomyPicturesState(
                        isLoading = false,
                        astronomyPictures = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = AstronomyPicturesState(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = AstronomyPicturesState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}