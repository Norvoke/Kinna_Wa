package com.norvoke.kinnawa

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.norvoke.kinnawa.ui.theme.KinnaWaTheme
import java.io.InputStream

val consonants = listOf(
    "𐴀", "𐴁", "𐴂", "𐴃", "𐴄", "𐴅", "𐴆",
    "𐴇", "𐴈", "𐴉", "𐴊", "𐴋", "𐴌", "𐴍",
    "𐴎", "𐴏", "𐴐", "𐴑", "𐴒", "𐴓", "𐴔",
    "𐴕", "𐴖", "\uD803\uDD17", "𐴘", "\uD803\uDD19", "𐴚", "𐴛"
)

val names = listOf(
    "aa", "ba", "pa", "tha", "ta", "ja", "cha",
    "ha", "kha", "fa", "dha", "da", "ra", "rda",
    "za", "sa", "sha", "ka", "ga", "la", "ma",
    "na", "wa", "kinna_wa", "ya", "kinna_ya", "nga", "nya"
)

class Stage3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KinnaWaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Stage3Screen(
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
fun Stage3ScreenPreview() {
    KinnaWaTheme {
        Stage3Screen(onBackClick = {})
    }
}

@Composable
fun Stage3Screen(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    var paths by remember { mutableStateOf(mutableListOf<List<Offset>>()) }
    var currentPath by remember { mutableStateOf(listOf<Offset>()) }
    var consonantIndex by remember { mutableStateOf(0) }
    val context = LocalContext.current

    // Load the bitmap for the current consonant
    val letterImage: Bitmap? = remember(consonantIndex) {
        loadBitmapFromAssets(context, "images/${names[consonantIndex]}.png")
    }

    Box(modifier = modifier.fillMaxSize()) {
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
                            paths = (paths + listOf(currentPath)).toMutableList() // Add the path to paths list when drag ends
                            currentPath = emptyList() // Reset current path
                        }
                    }
                ) { change, _ ->
                    currentPath = currentPath + change.position // Add points to the current path
                }
            }
        ) {
            letterImage?.let { img ->
                val imageBitmap = img.asImageBitmap()
                val offsetX = (size.width - imageBitmap.width) / 2
                val offsetY = (size.height - imageBitmap.height) / 2
                drawImage(image = imageBitmap, topLeft = Offset(offsetX, offsetY))
            }

            // Drawing the paths with an elliptical brush
            paths.forEach { path ->
                drawPathWithEllipticalBrush(path, Color.Red)
            }
            drawPathWithEllipticalBrush(currentPath, Color.DarkGray)
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
                        paths = mutableListOf() // Clear paths when changing letters
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
                        paths = mutableListOf() // Clear paths when changing letters
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

fun DrawScope.drawPathWithEllipticalBrush(path: List<Offset>, color: Color) {
    if (path.size < 2) return  // Need at least two points to calculate direction
    for (i in 1 until path.size) {
        val start = path[i - 1]
        val end = path[i]
        val angle = Math.atan2((end.y - start.y).toDouble(), (end.x - start.x).toDouble()).toFloat()

        drawOval(
            color = color,
            topLeft = Offset(start.x - 60, start.y - 50),
            size = androidx.compose.ui.geometry.Size(60f, 120f),
            alpha = 1f,
        )
    }
}

// Helper function to load bitmap from assets
fun loadBitmapFromAssets(context: Context, path: String): Bitmap? {
    return try {
        context.assets.open(path).use { inputStream: InputStream ->
            BitmapFactory.decodeStream(inputStream)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
