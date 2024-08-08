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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

// Main activity for Stage 1: Learning the alphabet
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

// Composable function for the main screen of learning the alphabet
@Composable
fun AlphabetLearningScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit, context: Context = LocalContext.current) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT

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

    // Layout for portrait mode
    if (isPortrait) {
        Column(
            modifier = modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "\uD803\uDD11\uD803\uDD1E\uD803\uDD15\uD803\uDD27\uD803\uDD1D \uD803\uDD07\uD803\uDD25\uD803\uDD21\uD803\uDD0C\uD803\uDD1F\uD803\uDD09\uD803\uDD22 / Learn Letters",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 24.dp),
                textAlign = TextAlign.Center
            )
            AlphabetSection(context, "", consonants, names, onLetterClick = { letter, name -> selectedLetter = Pair(letter, name) })
            Spacer(modifier = Modifier.height(16.dp))
            DisplaySection(selectedLetter)
            NavigationButtons(onBackClick)
        }
    }
    // Layout for landscape mode
    else {
        Row(
            modifier = modifier.fillMaxSize().padding(1.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "\uD803\uDD11\uD803\uDD1E\uD803\uDD15\uD803\uDD27\uD803\uDD1D \uD803\uDD07\uD803\uDD21\uD803\uDD25\uD803\uDD0C\uD803\uDD21\uD803\uDD09\uD803\uDD22 / Learn Letters",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(bottom = 2.dp),
                    textAlign = TextAlign.Center
                )
                DisplaySection(selectedLetter)
                NavigationButtons(onBackClick)
            }
            AlphabetSection(
                context = context,
                title = "",
                letters = consonants,
                names = names,
                onLetterClick = { letter, name -> selectedLetter = Pair(letter, name) },
                modifier = Modifier.weight(1f).fillMaxHeight()
            )
        }
    }
}

// Composable function to display the selected letter
@Composable
fun DisplaySection(selectedLetter: Pair<String, String>?) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "\uD803\uDD07\uD803\uDD21\uD803\uDD25\uD803\uDD0C\uD803\uDD21\uD803\uDD09\uD803\uDD22 / Letter:",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        if (selectedLetter != null) {
            Text(
                text = "${selectedLetter.first} - ${selectedLetter.second}",
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
    }
}

// Composable function for the navigation buttons
@Composable
fun NavigationButtons(onBackClick: () -> Unit) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT

    val buttonWidth = if (isPortrait) 250.dp else 90.dp
    val buttonHeight = if (isPortrait) 60.dp else 60.dp

    Spacer(modifier = Modifier.height(8.dp))

    Button(
        onClick = onBackClick,
        modifier = Modifier.height(buttonHeight).width(buttonWidth)
    ) {
        Text(
            style = MaterialTheme.typography.headlineLarge,
            text = "⬅\uFE0F"
        )
    }
}

// Composable function to display the alphabet section with buttons for each consonant
@Composable
fun AlphabetSection(
    context: Context,
    title: String,
    letters: List<String>,
    names: List<String>,
    onLetterClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT
    val gridSize = if (isPortrait) 60.dp else 58.dp

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Text(text = title, style = MaterialTheme.typography.titleLarge)
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = gridSize),
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

// Function to play the sound associated with a letter
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

// Preview function for the AlphabetLearningScreen composable
@Preview(showBackground = true)
@Composable
fun AlphabetLearningScreenPreview() {
    KinnaWaTheme {
        AlphabetLearningScreen(onBackClick = {})
    }
}
