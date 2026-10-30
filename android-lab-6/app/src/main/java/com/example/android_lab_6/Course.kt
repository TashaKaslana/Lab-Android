package com.example.android_lab_6

data class Course(
    val id: Int,
    val title: String,
    val author: String
)

sealed interface CourseUiState {
    data object Loading : CourseUiState

    data class Success(
        val list: List<Course>
    ) : CourseUiState

    data class Error(
        val message: String
    ) : CourseUiState
}