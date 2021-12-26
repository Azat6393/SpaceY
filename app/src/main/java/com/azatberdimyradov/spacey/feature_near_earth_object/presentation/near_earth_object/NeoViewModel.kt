package com.azatberdimyradov.spacey.feature_near_earth_object.presentation.near_earth_object

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azatberdimyradov.spacey.core.Resource
import com.azatberdimyradov.spacey.feature_near_earth_object.domain.use_case.GetNeo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NeoViewModel @Inject constructor(
    private val getNeo: GetNeo
) : ViewModel() {

    private val _state = mutableStateOf(NeoState())
    val state: State<NeoState> = _state

    init {
        val today = SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())
            .format(Date(System.currentTimeMillis()))
        getData(today, today)
    }

    fun getData(start_date: String, end_date: String) {
        getNeo(start_date = start_date, end_date = end_date).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = NeoState(
                        isLoading = false,
                        neo = result.data
                    )
                }
                is Resource.Error -> {
                    _state.value = NeoState(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = NeoState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}