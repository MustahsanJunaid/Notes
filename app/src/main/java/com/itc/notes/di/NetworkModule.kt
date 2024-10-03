package com.itc.notes.di

import com.itc.notes.data.remote.NotesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.example.com/")
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): NotesApi {
        return retrofit.create(NotesApi::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideNotesDao(database: RoomDatabase): NotesDao{
//        // here we'll initialise Dao using room DB.
//    }
}