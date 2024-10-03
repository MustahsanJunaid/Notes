package com.itc.notes.di

import com.itc.notes.data.remote.NotesApi
import com.itc.notes.data.repository.NoteRepository
import com.itc.notes.data.repository.RemoteNoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideNoteRepository(api: NotesApi): NoteRepository {
        return RemoteNoteRepository(api)
    }
}