package com.azatberdimyradov.spacey.feature_near_earth_object.presentation.asteroid_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azatberdimyradov.spacey.core.Constants
import com.azatberdimyradov.spacey.core.Resource
import com.azatberdimyradov.spacey.feature_near_earth_object.domain.use_case.GetAsteroidById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AsteroidViewModel @Inject constructor(
    private val getAsteroidById: GetAsteroidById,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AsteroidState())
    val state: State<AsteroidState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_ASTEROID_ID)?.let { asteroidId ->
            getAsteroid(asteroid_id = asteroidId)
        }
    }

    private fun getAsteroid(asteroid_id: String) {
        getAsteroidById(asteroid_id = asteroid_id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AsteroidState(
                        isLoading = false,
                        asteroid = result.data
                    )
                }
                is Resource.Error -> {
                    _state.value = AsteroidState(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = AsteroidState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}