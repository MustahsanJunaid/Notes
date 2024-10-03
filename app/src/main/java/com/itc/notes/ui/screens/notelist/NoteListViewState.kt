package com.itc.notes.ui.screens.notelist

data class NoteListViewState(
    val notes: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)