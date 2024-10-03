package com.itc.notes.ui.screens.notelist

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.hilt.navigation.compose.hiltViewModel
import com.itc.notes.domain.model.Note
import com.itc.notes.ui.common.CircularIndeterminateProgressBar
import com.itc.notes.ui.common.UiState
import com.itc.notes.ui.theme.NotesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    navController: NavHostController,
    noteListViewModel: NoteListViewModel = hiltViewModel()
) {
    val notesUiState by noteListViewModel.noteListUiState.collectAsState()

    // Scaffold with a TopAppBar
    NotesTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "My Tasks",
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    },
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu Icon",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            },
            content = { paddingValues ->
                when (notesUiState) {
                    is UiState.Loading -> {
                        CircularIndeterminateProgressBar(verticalBias = 0.5f)
                    }

                    is UiState.Success<*> -> {
                        NoteListContent(
                            paddingValues,
                            navController,
                            (notesUiState as UiState.Success<List<Note>>).data,
                        ) { newNote ->
                            noteListViewModel.addNote(newNote)
                        }
                    }

                    is UiState.Error -> {
                        Text(text = "Something went wrong")
                    }
                }

            }
        )
    }
}

@Composable
private fun NoteListContent(
    paddingValues: PaddingValues,
    navController: NavHostController,
    notes: List<Note>,
    addNoteClicked: (newNote: Note) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(notes.size) { index ->
                TaskRow(note = notes[index], onTaskClick = {
                    navController.navigate("taskDetail/$index")
                })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        var newNote by remember { mutableStateOf(TextFieldValue("")) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = newNote,
                onValueChange = { newNote = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .height(40.dp)
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.onSurface,
                        RoundedCornerShape(8.dp)
                    )
                    .background(
                        MaterialTheme.colorScheme.surface,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Start
                ),
                decorationBox = { innerTextField ->
                    if (newNote.text.isEmpty()) {
                        Text(
                            text = "Enter a new task...",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            )
                        )
                    }
                    innerTextField()
                }
            )
            Button(
                onClick = {
                    if (newNote.text.isNotBlank()) {
                        addNoteClicked(com.itc.notes.domain.model.Note(newNote.text))
                        newNote = TextFieldValue("")
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Add Task")
            }
        }
    }
}

@Composable
fun TaskRow(note: Note, onTaskClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onTaskClick)
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Text(
            text = note.content,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun TaskListScreenLightModePreview() {
    val navController = rememberNavController()
    NoteListScreen(navController = navController)
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TaskListScreenDarkModePreview() {
    val navController = rememberNavController()
    NoteListScreen(navController = navController)
}
