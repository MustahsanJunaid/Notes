package com.itc.notes.ui.screens.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itc.notes.data.repository.NoteRepository
import com.itc.notes.data.repository.RemoteNoteRepository
import com.itc.notes.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(NoteListViewState())
    val viewState: StateFlow<NoteListViewState> = _viewState.asStateFlow()

    fun handleIntent(intent: NoteListIntent) {
        when (intent) {
            is NoteListIntent.LoadNotes -> loadNotes()
            is NoteListIntent.AddNoteIntent -> addNoteToRepo(intent.note)
            is NoteListIntent.DeleteNoteIntent -> deleteNoteFromRepo(intent.noteId)
        }
    }

    private fun loadNotes() {
        _viewState.value = NoteListViewState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val notes = noteRepository.getNotes()
                _viewState.value = NoteListViewState(notes = notes)
            } catch (e: Exception) {
                _viewState.value = NoteListViewState(error = e.message)
            }
        }
    }

    private fun addNoteToRepo(note: String) {
        viewModelScope.launch(Dispatchers.IO) {
            // Handle adding note
        }
    }

    private fun deleteNoteFromRepo(noteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            // Handle deleting note
        }
    }
}