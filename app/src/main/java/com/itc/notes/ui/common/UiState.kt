package com.itc.notes.ui.common

sealed class UiState<out R> {
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val exception: Exception) : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
}