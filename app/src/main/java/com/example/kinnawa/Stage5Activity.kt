package com.example.kinnawa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kinnawa.ui.theme.KinnaWaTheme

class Stage5Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KinnaWaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StageScreen(
                        modifier = Modifier.padding(innerPadding),
                        text = "Stage 5: Complex Sentences and Grammar \n                     TBD",
                        onBackClick = { finish() }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Stage5ScreenPreview() {
    KinnaWaTheme {
        StageScreen(text = "Stage 5: Complex Sentences and Grammar", onBackClick = {})
    }
}
