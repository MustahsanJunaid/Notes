package com.itc.notes.ui.screens.notedatail

sealed class NoteDetailIntent {
    data class LoadNoteDetail(val noteId: Int) : NoteDetailIntent()
}