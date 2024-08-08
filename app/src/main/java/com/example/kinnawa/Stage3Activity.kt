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

class Stage3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KinnaWaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StageScreen(
                        modifier = Modifier.padding(innerPadding),
                        text = "Stage 3: \n                     TBD",
                        onBackClick = { finish() }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Stage2ScreenPreview() {
    KinnaWaTheme {
        StageScreen(text = "Stage 3: TBD", onBackClick = {})
    }
}
@Composable
fun StageScreen(modifier: Modifier = Modifier, text: String, onBackClick: () -> Unit) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Button(
                onClick = onBackClick,
                modifier = Modifier
                    .height(60.dp)
                    .width(250.dp)
            ) {
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = "Go Back"
                )
            }
        }
    }
}