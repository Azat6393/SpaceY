package com.azatberdimyradov.spacey.feature_epic.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azatberdimyradov.spacey.core.Resource
import com.azatberdimyradov.spacey.feature_epic.domain.use_case.GetEpic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import javax.inject.Inject

@SuppressLint("SimpleDateFormat")
@HiltViewModel
class EpicViewModel @Inject constructor(
    private val getEpic: GetEpic
) : ViewModel() {

    private val _state = mutableStateOf(EpicState())
    val state: State<EpicState> = _state

    init {
        val currentYear = SimpleDateFormat("yyyy")
            .format(System.currentTimeMillis())
        val currentMonth = SimpleDateFormat("MM")
            .format(System.currentTimeMillis())
        val currentDay = SimpleDateFormat("dd")
            .format(System.currentTimeMillis()).toInt() - 1
        getData("$currentYear-$currentMonth-$currentDay")
    }

    fun getData(date: String) {
        getEpic(date).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = EpicState(isLoading = false, epic = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = EpicState(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = EpicState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}