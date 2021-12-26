package com.azatberdimyradov.spacey.feature_people_in_space.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azatberdimyradov.spacey.core.Resource
import com.azatberdimyradov.spacey.feature_people_in_space.domain.use_case.GetPeopleInSpace
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PeopleInSpaceViewModel @Inject constructor(
    private val getPeopleInSpace: GetPeopleInSpace
) : ViewModel() {

    private val _state = mutableStateOf(PeopleInSpaceState())
    val state: State<PeopleInSpaceState> = _state

    init {
        getData()
    }

    private fun getData() {
        getPeopleInSpace().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value =
                        PeopleInSpaceState(peopleInSpace = result.data, isLoading = false)
                }
                is Resource.Error -> {
                    _state.value = PeopleInSpaceState(
                        error = result.message ?: "An unexpected error occurred",
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _state.value = PeopleInSpaceState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}