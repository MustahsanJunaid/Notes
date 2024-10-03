package com.itc.notes.ui.screens.notedatail

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.itc.notes.R
import com.itc.notes.ui.common.CircularIndeterminateProgressBar
import com.itc.notes.ui.common.UiState
import com.itc.notes.ui.theme.NotesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(
    taskId: Int,
    viewModel: NoteDetailViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val viewState by viewModel.viewState.collectAsState()

    // Trigger getNote when the screen first composes using LaunchedEffect
    LaunchedEffect(taskId) {
        viewModel.handleIntent(NoteDetailIntent.LoadNoteDetail(taskId))

    }

    NotesTheme {
        Scaffold(topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.screen_task_detail),
                        style = MaterialTheme.typography.headlineMedium,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back Button Icon",
                            tint = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }) { contentPadding ->

            when {
                viewState.isLoading -> {
                    CircularIndeterminateProgressBar(verticalBias = 0.5f)
                }

                viewState.error != null -> {
                    Text(text = "Something went wrong")
                }

                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(contentPadding)
                            .padding(16.dp) // Custom padding inside content
                    ) {
                        Text(
                            text = "Task ID: $taskId",
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Details for Task ID: $taskId",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = viewState.note.orEmpty(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    locale = "en"
)
@Composable
fun TaskDetailScreenLightModePreview() {
    val navController = rememberNavController()
    NoteDetailScreen(taskId = 1, navController = navController)
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun TaskDetailScreenDarkModePreview() {
    val navController = rememberNavController()
    NoteDetailScreen(taskId = 1, navController = navController)
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    locale = "ar"
)
@Composable
fun TaskDetailScreenArabicModePreview() {
    val navController = rememberNavController()
    NoteDetailScreen(taskId = 1, navController = navController)
}