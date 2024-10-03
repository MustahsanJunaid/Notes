package com.itc.notes.repository

import com.itc.notes.domain.repository.NoteRepository

class FakeNoteRepository: NoteRepository {
    override suspend fun getNotes(): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getNoteDetail(noteId: Int): String? {
        TODO("Not yet implemented")
    }

    override suspend fun addNote(newNote: String): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(noteId: Int): List<String> {
        TODO("Not yet implemented")
    }
}