package com.example.andoird_lab_2.layout

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


data class Student(val id: Int, val name: String, val major: String)

val student_list = listOf(
    Student(1, "Nguyễn Văn An", "Công nghệ thông tin"),
    Student(2, "Trần Thị Bích", "Công nghệ thông tin"),
    Student(3, "Lê Hoàng Minh", "Công nghệ thông tin")
)

@Composable
fun Home(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var isOpenSearch by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    val filterStudents = student_list.filter { student ->
        student.name.contains(searchQuery, ignoreCase = true)
                || student.major.contains(searchQuery, ignoreCase = true
        )
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Green)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu"
                        )
                    }

                    Text("Danh sách sinh viên")


                    if (!isOpenSearch) {
                        IconButton(onClick = {
                            isOpenSearch = true
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Search"
                            )
                        }
                    } else {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            placeholder = { Text("Tìm sinh viên...") },
                            leadingIcon = {
                                IconButton(
                                    onClick = {
                                        isOpenSearch = false
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Search,
                                        contentDescription = "Search"
                                    )
                                }
                            },
                            trailingIcon = {
                                if (searchQuery.isNotEmpty()) {
                                    IconButton(onClick = { searchQuery = "" }) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Clear search"
                                        )
                                    }
                                }
                            },
                            singleLine = true
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                shape = CircleShape,
                containerColor = Color.Cyan
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filterStudents) { student ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            Surface(
                                modifier = Modifier.size(48.dp),
                                shape = CircleShape,
                                color = Color.Cyan
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = student.name.firstOrNull()?.uppercaseChar()
                                            ?.toString() ?: "?",
                                        style = MaterialTheme.typography.titleLarge,
                                        color = Color.Blue,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Column(
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = student.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = student.major,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray
                                )
                            }
                        }


                        Button(
                            onClick = {
                                Toast.makeText(
                                    context,
                                    "Id: ${student.id}\nSinh viên: ${student.name}\nNgành: ${student.major}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        ) {
                            Text("Xem Chi Tiết")
                        }
                    }
                }
            }
        }
    }
}