package com.itc.notes.ui.screens.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itc.notes.domain.model.Note
import com.itc.notes.domain.usecase.AddNoteUseCase
import com.itc.notes.domain.usecase.DeleteNoteUseCase
import com.itc.notes.domain.usecase.GetNotesUseCase
import com.itc.notes.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _noteListUiState = MutableStateFlow<UiState>(UiState.Loading)
    val noteListUiState: StateFlow<UiState> = _noteListUiState

//    private val _notes = MutableStateFlow<List<String>>(emptyList())
//    val notes: StateFlow<List<String>> = _notes

    init {
        loadNotes()
    }

    private fun loadNotes() {
        _noteListUiState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val notes = getNotesUseCase.execute()
                _noteListUiState.value = UiState.Success(notes)
            } catch (e: Exception) {
                _noteListUiState.value = UiState.Error(e)
            }
        }
    }

    fun addNote(newNote: Note) {
        _noteListUiState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                addNoteUseCase.execute(newNote)
                val updatedNotes = getNotesUseCase.execute()  // Refresh the list
                _noteListUiState.value = UiState.Success(updatedNotes)
            } catch (e: Exception) {
                _noteListUiState.value = UiState.Error(e)
            }
        }
    }

    fun deleteNote(noteId: Int) {
        _noteListUiState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                deleteNoteUseCase.execute(noteId)
                val updatedNotes = getNotesUseCase.execute()  // Refresh the list
                _noteListUiState.value = UiState.Success(updatedNotes)
            } catch (e: Exception) {
                _noteListUiState.value = UiState.Error(e)
            }
        }
    }
}