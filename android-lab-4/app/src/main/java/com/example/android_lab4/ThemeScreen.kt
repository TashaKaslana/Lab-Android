package com.example.themeapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeScreen(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
) {
    val colors = MaterialTheme.colorScheme

    Scaffold(
        containerColor = colors.surface,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Giao diện & Theme",
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = colors.primary,
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = null,
                            tint = colors.primary,
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colors.surface,
                    titleContentColor = colors.onSurface,
                ),
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.surface)
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            DarkModeToogleSwitch(colors, isDarkTheme, onThemeChange)

            Text(
                text = "MÀU SẮC (ROLES)",
                color = colors.primary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                ColorRoleChip("Primary", selected = true)
                ColorRoleChip("Secondary")
                ColorRoleChip("Surface")
            }

            Text(
                text = "XEM TRƯỚC THEME",
                color = colors.primary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
            )

            PreviewColorCard(colors, onThemeChange, isDarkTheme)

            Row() {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = null
                )

                Text("Các thay đổi sẽ được áp dụng ngay lập tức trên toàn ứng dụng")
            }
        }
    }
}

@Composable
private fun DarkModeToogleSwitch(
    colors: ColorScheme,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = colors.surface,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.Brightness4,
                contentDescription = null,
                tint = colors.primary,
            )

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = "Chế độ tối (Dark Mode)",
                    color = colors.onSurface,
                    fontWeight = FontWeight.Medium,
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = "Tùy chỉnh giao diện sáng hoặc tối",
                    color = colors.onSurface.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                )
            }

            Switch(
                checked = isDarkTheme,
                onCheckedChange = onThemeChange,
            )
        }
    }
}

@Composable
private fun PreviewColorCard(
    colors: ColorScheme,
    onThemeChange: (Boolean) -> Unit,
    isDarkTheme: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = colors.surface,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Palette,
                    contentDescription = null,
                    tint = colors.primary,
                )

                Spacer(Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Tiêu đề thẻ",
                        color = colors.onSurface,
                        fontWeight = FontWeight.SemiBold,
                    )

                    Text(
                        text = "Đây là nội dung mẫu của thẻ.",
                        color = colors.onSurface.copy(alpha = 0.7f),
                        fontSize = 12.sp,
                    )

                    Text(
                        text = "Nó giúp bạn xem trước giao diện.",
                        color = colors.onSurface.copy(alpha = 0.7f),
                        fontSize = 12.sp,
                    )
                }
            }

            HorizontalDivider()

            Button(
                onClick = {
                    onThemeChange(!isDarkTheme)
                },
            ) {
                Text("HÀNH ĐỘNG")
            }
        }
    }
}

@Composable
private fun ColorRoleChip(
    text: String,
    selected: Boolean = false,
) {
    val colors = MaterialTheme.colorScheme

    Surface(
        shape = RoundedCornerShape(8.dp),
        color = if (selected) {
            colors.primary.copy(alpha = 0.12f)
        } else {
            colors.surface
        },
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (selected) colors.primary
            else colors.onSurface.copy(alpha = 0.2f),
        ),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 10.dp,
            ),
            color = if (selected) colors.primary else colors.onSurface,
            fontSize = 12.sp,
        )
    }
}
