package com.itc.notes.di

import com.itc.notes.domain.repository.NoteRepository
import com.itc.notes.domain.usecase.AddNoteUseCase
import com.itc.notes.domain.usecase.DeleteNoteUseCase
import com.itc.notes.domain.usecase.GetNoteDetailUseCase
import com.itc.notes.domain.usecase.GetNotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetNotesUseCase(noteRepository: NoteRepository): GetNotesUseCase {
        return GetNotesUseCase(noteRepository)
    }

    @Provides
    fun provideGetNoteDetailUseCase(noteRepository: NoteRepository): GetNoteDetailUseCase {
        return GetNoteDetailUseCase(noteRepository)
    }

    @Provides
    fun provideAddNoteUseCase(noteRepository: NoteRepository): AddNoteUseCase {
        return AddNoteUseCase(noteRepository)
    }

    @Provides
    fun provideDeleteNoteUseCase(noteRepository: NoteRepository): DeleteNoteUseCase {
        return DeleteNoteUseCase(noteRepository)
    }
}