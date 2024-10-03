package com.itc.notes.ui.screens.notelist

sealed class NoteListIntent {
    data object LoadNotes : NoteListIntent()
    data class AddNoteIntent(val note: String) : NoteListIntent()
    data class DeleteNoteIntent(val noteId: Int) : NoteListIntent()
}