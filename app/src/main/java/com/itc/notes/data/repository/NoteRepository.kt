package com.itc.notes.data.repository

interface NoteRepository {

    suspend fun getNotes(): List<String>

    suspend fun getNoteDetail(noteId: Int): String?

    suspend fun addNote(newNote: String): List<String>

    suspend fun deleteNote(noteId: Int): List<String>
}

interface GetNotes{
    suspend fun getNotes(): List<String>
}

interface GetNoteDetails{
    suspend fun getNoteDetail(noteId: Int): String?
}