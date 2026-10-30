package com.example.android_lab_6

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class CourseViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<CourseUiState>(CourseUiState.Loading)
    val uiState: StateFlow<CourseUiState> = _uiState.asStateFlow()
    private var loadCount = 0

    init {
        load()
    }

    val sampleCourse = listOf(
        Course(1, "Kotlin cơ bản", "Nguyễn Văn A"),
        Course(2, "Jetpack Compose", "Trần Thị B"),
        Course(3, "Android nâng cao", "Lê Văn C")
    )

    fun load() {
        val attempt = loadCount
        loadCount++
        viewModelScope.launch {
            _uiState.value = CourseUiState.Loading

            delay(1200.milliseconds)

            _uiState.value = if (attempt % 2 == 0)
                CourseUiState.Success(sampleCourse)
            else CourseUiState.Error("Không tải được dữ liệu. Thử lại!")
        }
    }
}