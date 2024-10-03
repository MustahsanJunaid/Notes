package com.itc.notes.domain.usecase

import com.itc.notes.domain.model.Note
import com.itc.notes.domain.repository.NoteRepository

class GetNotesUseCase(private val noteRepository: NoteRepository) {
    suspend fun execute(): List<Note> {
        return noteRepository.getNotes()
    }
}