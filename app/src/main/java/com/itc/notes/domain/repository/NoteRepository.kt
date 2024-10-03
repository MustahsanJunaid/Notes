package com.itc.notes.domain.repository

import com.itc.notes.domain.model.Note

interface NoteRepository {

    suspend fun getNotes(): List<Note>

    suspend fun getNoteDetail(noteId: Int): Note?

    suspend fun addNote(newNote: Note): List<Note>

    suspend fun deleteNote(noteId: Int): List<Note>
}

interface GetNotes{
    suspend fun getNotes(): List<Note>
}

interface GetNoteDetails{
    suspend fun getNoteDetail(noteId: Int): Note?
}