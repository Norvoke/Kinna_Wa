package com.norvoke.kinnawa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.norvoke.kinnawa.ui.theme.KinnaWaTheme

val words = listOf(
    "\uD803\uDD14\uD803\uDD1D\uD803\uDD15\uD803\uDD1F\uD803\uDD24\uD803\uDD1E\uD803\uDD10",
    "\uD803\uDD01\uD803\uDD20\uD803\uDD12\uD803\uDD27\uD803\uDD1F\uD803\uDD24\uD803\uDD15",
    "\uD803\uDD1D\uD803\uDD0E\uD803\uDD1D\uD803\uDD0A",
    "\uD803\uDD07\uD803\uDD1E\uD803\uDD24\uD803\uDD0F\uD803\uDD1D\uD803\uDD09\uD803\uDD20",
    "\uD803\uDD1D\uD803\uDD0C",
    "\uD803\uDD1E\uD803\uDD0E\uD803\uDD27\uD803\uDD21\uD803\uDD03\uD803\uDD1D\uD803\uDD0C\uD803\uDD0A\uD803\uDD20",
    "\uD803\uDD07\uD803\uDD21\uD803\uDD24\uD803\uDD11",
    "\uD803\uDD21\uD803\uDD24\uD803\uDD11\uD803\uDD27\uD803\uDD21\uD803\uDD13",
    "\uD803\uDD21\uD803\uDD24\uD803\uDD03",
    "\uD803\uDD09\uD803\uDD1F\uD803\uDD24\uD803\uDD1D\uD803\uDD1E\uD803\uDD15\uD803\uDD27\uD803\uDD1D",
    "\uD803\uDD07\uD803\uDD1E\uD803\uDD24\uD803\uDD0F\uD803\uDD1D\uD803\uDD09\uD803\uDD20",
    "\uD803\uDD09\uD803\uDD21\uD803\uDD1E\uD803\uDD0A\uD803\uDD1D",
    "\uD803\uDD21\uD803\uDD24\uD803\uDD1E\uD803\uDD18\uD803\uDD20",
    "\uD803\uDD09\uD803\uDD21\uD803\uDD03\uD803\uDD27\uD803\uDD1E\uD803\uDD24"
)

class Stage5Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KinnaWaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Stage5Screen(
                        modifier = Modifier.padding(innerPadding),
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
        Stage5Screen(onBackClick = {})
    }
}

@Composable
fun Stage5Screen(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    var paths by remember { mutableStateOf(mutableListOf<List<Offset>>()) }
    var currentPath by remember { mutableStateOf(listOf<Offset>()) }
    var consonantIndex by remember { mutableStateOf(0) }

    Box(modifier = modifier.fillMaxSize()) {
        // Drawing canvas
        Canvas(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        currentPath = listOf(offset) // Start a new path on drag start
                    },
                    onDragEnd = {
                        if (currentPath.isNotEmpty()) {
                            paths =
                                (paths + listOf(currentPath)).toMutableList() // Add the path to paths list when drag ends
                            currentPath = emptyList() // Reset current path
                        }
                    }
                ) { change, _ ->
                    currentPath = currentPath + change.position // Add points to the current path
                }
            }
        ) {
            // Draw the current word in the background
            drawContext.canvas.nativeCanvas.apply {
                val character = words[consonantIndex]
                drawText(
                    character,
                    (size.width / 2),
                    (size.height / 2),
                    android.graphics.Paint().apply {
                        color = android.graphics.Color.RED
                        textAlign = android.graphics.Paint.Align.CENTER
                        textSize = 200f
                    }
                )
            }

            // Draw each stored path
            paths.forEach { path ->
                for (i in 1 until path.size) {
                    drawLine(
                        color = Color.Black,
                        start = path[i - 1],
                        end = path[i],
                        strokeWidth = 20f,
                        cap = StrokeCap.Round
                    )
                }
            }
            // Draw the current in-progress path
            for (i in 1 until currentPath.size) {
                drawLine(
                    color = Color.DarkGray,
                    start = currentPath[i - 1],
                    end = currentPath[i],
                    strokeWidth = 20f,
                    cap = StrokeCap.Round
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Consonant Navigation
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(
                    onClick = {
                        if (consonantIndex > 0) consonantIndex--
                        paths = mutableListOf() //
                    },
                    modifier = Modifier
                        .height(60.dp)
                        .width(150.dp)
                ) {
                    Text(
                        style = MaterialTheme.typography.titleLarge,
                        text = "Previous"
                    )
                }
                Button(
                    onClick = {
                        if (consonantIndex < consonants.size - 1) consonantIndex++
                        paths = mutableListOf() //
                    },
                    modifier = Modifier
                        .height(60.dp)
                        .width(150.dp)
                ) {
                    Text(
                        style = MaterialTheme.typography.titleLarge,
                        text = "Next"
                    )
                }
            }

            // Clear and Go Back Buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onBackClick,
                    modifier = Modifier
                        .height(60.dp)
                        .width(150.dp)
                ) {
                    Text(
                        style = MaterialTheme.typography.titleLarge,
                        text = "Go Back"
                    )
                }

                Button(
                    onClick = {
                        paths = mutableListOf() // Clear all paths
                    },
                    modifier = Modifier
                        .height(60.dp)
                        .width(150.dp)
                ) {
                    Text(
                        style = MaterialTheme.typography.titleLarge,
                        text = "Clear"
                    )
                }
            }
        }
    }
}