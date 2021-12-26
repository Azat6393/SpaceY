package com.azatberdimyradov.spacey.feature_epic.presentation

import com.azatberdimyradov.spacey.feature_epic.domain.model.Epic

data class EpicState(
    val isLoading: Boolean = false,
    val epic: List<Epic> = emptyList(),
    val error: String = ""
)
