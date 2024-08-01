package com.example.kinnawa

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kinnawa.ui.theme.KinnaWaTheme
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.runtime.CompositionLocalProvider
import android.content.Context
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import java.util.Locale


class Stage1Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KinnaWaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AlphabetLearningScreen(modifier = Modifier.padding(innerPadding), onBackClick = { finish() })
                }
            }
        }
    }
}


@Composable
fun AlphabetLearningScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit, context: Context = LocalContext.current) {
    val consonants = listOf(
        "𐴀", "𐴁", "𐴂", "𐴃", "𐴄", "𐴅", "𐴆",
        "𐴇", "𐴈", "𐴉", "𐴊", "𐴋", "𐴌", "𐴍",
        "𐴎", "𐴏", "𐴐", "𐴑", "𐴒", "𐴓", "𐴔",
        "𐴕", "𐴖", "\uD803\uDD17", "𐴘", "\uD803\uDD19", "𐴚", "𐴛"
    )

    val names = listOf(
        "Aa", "Ba", "Pa", "Tha", "Ta", "Ja", "Cha",
        "Ha", "Kha", "Fa", "Dha", "Da", "Ra", "Rda",
        "Za", "Sa", "Sha", "Ka", "Ga", "La", "Ma",
        "Na", "Wa", "Kinna Wa", "Ya", "Kinna Ya", "Nga", "Nya"
    )

    var selectedLetter by remember { mutableStateOf<Pair<String, String>?>(null) }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Stage 1: Learn the Letters",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )
        AlphabetSection(context, "Consonants", consonants, names, onLetterClick = { letter, name -> selectedLetter = Pair(letter, name) })
        Spacer(modifier = Modifier.height(16.dp))
        // Heading for the selected letter display
        Text(
            text = "Selected Letter:",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        // Display the selected letter and its name or a placeholder
        if (selectedLetter != null) {
            Text(
                text = "${selectedLetter!!.first} - ${selectedLetter!!.second}",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Text(
                text = "No letter selected",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button(
            onClick = {
                context.startActivity(Intent(context, Stage1aActivity::class.java))
            },
            modifier = Modifier
                .height(60.dp)
                .width(250.dp)
                .padding(top = 16.dp)
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = "Next: Vowels"
            )
        }

        Spacer(modifier = Modifier.height(1.dp))
        Button(
            onClick = onBackClick,
            modifier = Modifier.height(60.dp).width(250.dp)
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = "Go Back"
            )
        }
    }
}


@Composable
fun AlphabetSection(
    context: Context, // Pass context to access resources
    title: String,
    letters: List<String>,
    names: List<String>,
    onLetterClick: (String, String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.titleLarge)
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 60.dp),
                contentPadding = PaddingValues(1.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(letters.zip(names)) { (letter, name) ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                onLetterClick(letter, name)
                                playLetterSound(context, name.lowercase(Locale.ROOT).replace(" ", "_"))
                            },
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(letter, style = MaterialTheme.typography.headlineMedium.copy(fontSize = 24.sp))
                        }
                    }
                }
            }
        }
    }
}

fun playLetterSound(context: Context, fileName: String) {
    val mediaPlayer = MediaPlayer()
    try {
        val descriptor = context.assets.openFd("audio/$fileName.mp3")
        mediaPlayer.setDataSource(descriptor.fileDescriptor, descriptor.startOffset, descriptor.length)
        descriptor.close()

        mediaPlayer.prepare()
        mediaPlayer.start()
    } catch (e: Exception) {
        e.printStackTrace() // Handle exceptions
    }
}




@Preview(showBackground = true)
@Composable
fun AlphabetLearningScreenPreview() {
    KinnaWaTheme {
        AlphabetLearningScreen(onBackClick = {})
    }
}
