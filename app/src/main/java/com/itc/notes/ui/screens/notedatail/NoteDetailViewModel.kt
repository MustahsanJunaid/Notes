package com.itc.notes.ui.screens.notedatail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itc.notes.data.repository.RemoteNoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteRepository: RemoteNoteRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(NoteDetailViewState())
    val viewState: StateFlow<NoteDetailViewState> = _viewState.asStateFlow()

    fun handleIntent(intent: NoteDetailIntent) {
        when (intent) {
            is NoteDetailIntent.LoadNoteDetail -> loadNoteDetail(intent.noteId)
        }
    }

    private fun loadNoteDetail(noteId: Int) {
        _viewState.value = NoteDetailViewState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val note = noteRepository.getNoteDetail(noteId)
            if (note != null) {
                _viewState.value = NoteDetailViewState(note = note)
            } else {
                _viewState.value = NoteDetailViewState(error = "Note not found")
            }
        }
    }
}