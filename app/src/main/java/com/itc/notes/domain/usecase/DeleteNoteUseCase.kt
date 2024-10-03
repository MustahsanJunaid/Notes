package com.itc.notes.domain.usecase

import com.itc.notes.domain.repository.NoteRepository

class DeleteNoteUseCase(private val noteRepository: NoteRepository) {
    suspend fun execute(noteId: Int) {
        noteRepository.deleteNote(noteId)
    }
}