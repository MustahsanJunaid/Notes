package com.itc.notes.domain.usecase

import com.itc.notes.domain.model.Note
import com.itc.notes.domain.repository.NoteRepository

class GetNoteDetailUseCase(private val noteRepository: NoteRepository) {
    suspend fun execute(noteId: Int): Note? {
        return noteRepository.getNoteDetail(noteId)
    }
}