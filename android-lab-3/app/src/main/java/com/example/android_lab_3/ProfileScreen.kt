package com.example.android_lab_3

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_lab_3.ui.theme.DividerGray
import com.example.android_lab_3.ui.theme.Teal
import com.example.android_lab_3.ui.theme.TealDark
import com.example.android_lab_3.ui.theme.TealLight


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDetailScreen(
    student: Student,
    onBack: () -> Unit,
    onCall: (String) -> Unit,
    onEmail: (String) -> Unit
) {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Chi tiết sinh viên",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector =
                                Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = TealDark
                )
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                .padding(horizontal = 16.dp)
        ) {
            AvatarPersonInfo(student)

            HorizontalDivider(Modifier, thickness = 1.dp, color = DividerGray)

            //to make the action bar bottom
            Column (modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.9f)) {
                // Email row
                StudentInfoRow(
                    label = "Email",
                    value = student.email,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                )

                HorizontalDivider(Modifier, thickness = 1.dp, color = DividerGray)

                // Phone row
                StudentInfoRow(
                    label = "Phone",
                    value = formatPhone(student.phone),
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                )

                HorizontalDivider(Modifier, thickness = 1.dp, color = DividerGray)
            }

            InfoActionButtons(onCall, student, onEmail)
        }
    }
}

@Composable
private fun InfoActionButtons(
    onCall: (String) -> Unit,
    student: Student,
    onEmail: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = { onCall(student.phone) },
            modifier = Modifier
                .weight(1f)
                .height(44.dp),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Teal,
                contentColor = Color.White
            )
        ) {
            Icon(
                Icons.Default.Phone,
                contentDescription = null,
                modifier = Modifier.size(17.dp)
            )
            Spacer(Modifier.size(6.dp))
            Text("Gọi điện", fontSize = 13.sp)
        }

        OutlinedButton(
            onClick = { onEmail(student.email) },
            modifier = Modifier
                .weight(1f)
                .height(44.dp),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Teal
            ),
            border = BorderStroke(
                1.dp, Teal
            )
        ) {
            Icon(
                Icons.Default.Email,
                contentDescription = null,
                modifier = Modifier.size(17.dp)
            )
            Spacer(Modifier.size(6.dp))
            Text(
                "Gửi email",
                fontSize = 13.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun ColumnScope.AvatarPersonInfo(student: Student) {
    Box(
        modifier = Modifier
            .padding(top = 12.dp)
            .size(86.dp)
            .align(Alignment.CenterHorizontally)
            .background(TealLight, CircleShape)
            .border(1.dp, Teal, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = student.name.firstOrNull()
                ?.uppercase() ?: "A",
            color = Teal,
            fontSize = 36.sp,
            fontWeight = FontWeight.Medium
        )
    }

    // Name
    Text(
        text = student.name,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        color = Color.Black,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )

    // Department
    Text(
        text = student.department,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 14.dp),
        color = Teal,
        fontSize = 14.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun StudentInfoRow(
    label: String,
    value: String,
    icon: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(
                    color = Teal,
                    shape = RoundedCornerShape(6.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = label,
                color = Color.Gray,
                fontSize = 12.sp
            )

            Text(
                text = value,
                modifier = Modifier.padding(top = 2.dp),
                color = Color.Black,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

private fun formatPhone(phone: String): String {
    return if (phone.length == 10 && phone.all(Char::isDigit)) {
        "${phone.substring(0, 4)} " +
                "${phone.substring(4, 7)} " +
                phone.substring(7)
    } else {
        phone
    }
}