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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _noteListUiState = MutableStateFlow<UiState<List<String>>>(UiState.Loading)
    val noteListUiState: StateFlow<UiState<List<String>>> = _noteListUiState

//    private val _notes = MutableStateFlow<List<String>>(emptyList())
//    val notes: StateFlow<List<String>> = _notes

    init {
        loadNotes()
    }

    private fun loadNotes() {
        _noteListUiState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _noteListUiState.value = UiState.Success(noteRepository.getNotes())
        }
    }

    fun addNote(newNote: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _noteListUiState.value = UiState.Loading
            _noteListUiState.value = UiState.Success(noteRepository.addNote(newNote))
        }
    }

    fun deleteNote(noteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _noteListUiState.value = UiState.Loading
            _noteListUiState.value = UiState.Success(noteRepository.deleteNote(noteId))
        }
    }
}