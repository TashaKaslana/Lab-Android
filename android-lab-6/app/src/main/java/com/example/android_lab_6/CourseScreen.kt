package com.example.android_lab_6

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseScreen(viewModel: CourseViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Khóa học")
                    }
                )
            }
        ) { innerPadding ->
            when (val state = uiState) {
                is CourseUiState.Loading -> {
                    LoadingContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }

                is CourseUiState.Success -> {
                    SuccessContent(
                        courses = state.list,
                        onRetry = viewModel::load,
                        modifier = Modifier.padding(innerPadding)
                    )
                }

                is CourseUiState.Error -> {
                    ErrorContent(
                        message = state.message,
                        onRetry = viewModel::load,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}