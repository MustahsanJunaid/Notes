package com.itc.notes.domain.usecase

import com.itc.notes.domain.model.Note
import com.itc.notes.domain.repository.NoteRepository

class AddNoteUseCase(private val noteRepository: NoteRepository) {
    suspend fun execute(note: Note) {
        noteRepository.addNote(note)
    }
}