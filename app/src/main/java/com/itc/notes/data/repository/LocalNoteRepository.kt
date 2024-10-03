package com.itc.notes.data.repository

import com.itc.notes.data.remote.NotesApi
import com.itc.notes.domain.repository.NoteRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

//class LocalNoteRepository @Inject constructor(
////    // here we'll inject data sources like api, or databases
//    val api: NotesApi
//) : NoteRepository {
//
//    // Simulated list of notes
//    private val noteList = mutableListOf("Note 1", "Note 2", "Note 3")
//
//    // Use coroutines to handle data operations asynchronously
//    override suspend fun getNotes(): List<String> {
//        // Simulate long-running operation (e.g., network or database access)
//        delay(3000L)
//        return noteList
//    }
//
//    override suspend fun getNoteDetail(noteId: Int): String? {
//        // Simulate long-running operation
//        delay(3000L)
//        return if (noteId in noteList.indices) noteList[noteId] else null
//    }
//
//    override suspend fun addNote(newNote: String): List<String> {
//        // Simulate long-running operation
//        delay(3000L)
//        noteList.add(newNote)
//        return noteList.toList()
//    }
//
//    override suspend fun deleteNote(noteId: Int): List<String> {
//        delay(3000L)
//        noteList.removeAt(noteId)
//        return noteList.toList()
//    }
//}