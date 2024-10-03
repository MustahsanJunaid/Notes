package com.itc.notes.ui.screens.notedatail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itc.notes.data.repository.NoteRepository
import com.itc.notes.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _noteUiState = MutableStateFlow<UiState<String>>(UiState.Loading)
    val noteUiState: StateFlow<UiState<String>> = _noteUiState

    fun getNote(noteId: Int) {
        _noteUiState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.getNoteDetail(noteId)?.let {
                _noteUiState.value = UiState.Success(it)
            } ?: run {
                _noteUiState.value = UiState.Error(Exception("Note not found"))
            }
        }
    }
}