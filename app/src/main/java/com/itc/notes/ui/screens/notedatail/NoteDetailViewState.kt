package com.itc.notes.ui.screens.notedatail

data class NoteDetailViewState(
    val note: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)