package com.example.android_lab_5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var uiState by remember {
                mutableStateOf<CourseUiState>(CourseUiState.Loading)
            }

            var reloadKey by remember {
                mutableIntStateOf(0)
            }

            LaunchedEffect(reloadKey) {
                uiState = CourseUiState.Loading

                delay(1200.milliseconds)

                uiState = if (reloadKey % 2 == 0) {
                    CourseUiState.Success(
                        listOf(
                            Course(1, "Kotlin cơ bản", "Nguyễn Văn A"),
                            Course(2, "Jetpack Compose", "Trần Thị B"),
                            Course(3, "Android nâng cao", "Lê Văn C")
                        )
                    )
                } else {
                    CourseUiState.Error(
                        "Không thể tải dữ liệu. Vui lòng thử lại."
                    )
                }
            }

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
                                onRetry = { reloadKey++ },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                        is CourseUiState.Error -> {
                            ErrorContent(
                                message = state.message,
                                onRetry = { reloadKey++ },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}
