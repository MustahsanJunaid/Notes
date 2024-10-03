package com.itc.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itc.notes.ui.screens.notedatail.NoteDetailScreen
import com.itc.notes.ui.screens.notelist.NoteListScreen
import com.itc.notes.ui.theme.NotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesApp()
        }
    }
}

@Composable
fun NotesApp() {
    NotesTheme(darkTheme = isSystemInDarkTheme()) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "taskList") {
            // Note List Screen
            composable("taskList") { NoteListScreen(navController) }

            // Note Details Screen
            composable("taskDetail/{taskId}") { backStackEntry ->
                NoteDetailScreen(
                    navController = navController,
                    taskId = (backStackEntry.arguments?.getString("taskId") ?: "0").toInt(),
                )
            }
        }
    }
}

enum class Routes {
    NoteList,
    NoteDetails
}