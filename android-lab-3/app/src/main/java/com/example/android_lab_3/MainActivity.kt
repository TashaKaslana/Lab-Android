package com.example.android_lab_3

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import com.example.android_lab_3.ui.theme.AndroidLab3Theme

data class Student(
    val name: String,
    val department: String,
    val email: String,
    val phone: String
)

val student = Student(
    name = "Nguyễn Văn An",
    department = "Công nghệ thông tin",
    email = "an.nguyen@example.com",
    phone = "0123456789"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            setContent {
                MaterialTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.White
                    ) {
                        StudentDetailScreen(
                            student = student,
                            onBack = { finish() },
                            onCall = { phone ->
                                Toast.makeText(
                                    this,
                                    "Phone: $phone",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            onEmail = { email ->
                                Toast.makeText(
                                    this,
                                    "Email: $email",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidLab3Theme {
        Greeting("Android")
    }
}