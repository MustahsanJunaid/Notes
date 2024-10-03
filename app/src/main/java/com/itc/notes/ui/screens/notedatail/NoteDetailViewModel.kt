package com.itc.notes.ui.screens.notedatail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itc.notes.domain.usecase.GetNoteDetailUseCase
import com.itc.notes.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val getNoteDetailUseCase: GetNoteDetailUseCase
) : ViewModel() {

    private val _noteUiState = MutableStateFlow<UiState>(UiState.Loading)
    val noteUiState: StateFlow<UiState> = _noteUiState

    fun getNoteDetails(noteId: Int) {
        _noteUiState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val notes = getNoteDetailUseCase.execute(noteId)
                _noteUiState.value = UiState.Success(notes)
            } catch (e: Exception) {
                _noteUiState.value = UiState.Error(e)
            }
        }
    }
}