package com.example.kinnawa

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kinnawa.ui.theme.KinnaWaTheme

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
fun AlphabetLearningScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    val consonants = listOf(
        "𐴀", "𐴁", "𐴂", "𐴃", "𐴄", "𐴅", "𐴆",
        "𐴇", "𐴈", "𐴉", "𐴊", "𐴋", "𐴌", "𐴍",
        "𐴎", "𐴏", "𐴐", "𐴑", "𐴒", "𐴓", "𐴔",
        "𐴕", "𐴖", "𐴘", "𐴚", "𐴛", "𐴜", "◌𐴧"
    )
    val vowels = listOf("𐴢", "𐴡", "𐴠", "𐴟", "𐴞", "𐴝", "𐴗", "𐴙")
    val toneMarks = listOf("◌𐴦", "◌𐴥", "◌𐴤")

    var selectedLetter by remember { mutableStateOf<String?>(null) }

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
        AlphabetSection(title = "Consonants", letters = consonants, onLetterClick = { selectedLetter = it })
        Spacer(modifier = Modifier.height(1.dp))
        AlphabetSection(title = "Vowels and Semi-Vowels", letters = vowels, onLetterClick = { selectedLetter = it })
        Spacer(modifier = Modifier.height(1.dp))
        AlphabetSection(title = "Tone Marks", letters = toneMarks, onLetterClick = { selectedLetter = it })
        Spacer(modifier = Modifier.height(1.dp))
        selectedLetter?.let {
            Text(
                text = "Selected Letter: $it",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
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
fun AlphabetSection(title: String, letters: List<String>, onLetterClick: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.titleLarge)
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 60.dp),
            contentPadding = PaddingValues(1.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(letters) { letter ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = { onLetterClick(letter) },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(letter, style = MaterialTheme.typography.headlineMedium.copy(fontSize = 24.sp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlphabetLearningScreenPreview() {
    KinnaWaTheme {
        AlphabetLearningScreen(onBackClick = {})
    }
}
