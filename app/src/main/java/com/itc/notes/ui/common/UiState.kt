package com.itc.notes.ui.common

sealed class UiState {
    data class Success<out T>(val data: T) : UiState()
    data class Error(val exception: Exception) : UiState()
    data object Loading : UiState()
}