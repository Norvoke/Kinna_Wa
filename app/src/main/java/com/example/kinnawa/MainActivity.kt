package com.example.kinnawa

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kinnawa.ui.theme.KinnaWaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KinnaWaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        navigateToStage1 = { navigateTo(Stage1Activity::class.java) },
                        navigateToStage2 = { navigateTo(Stage2Activity::class.java) },
                        navigateToStage3 = { navigateTo(Stage3Activity::class.java) },
                        navigateToStage4 = { navigateTo(Stage4Activity::class.java) },
                        navigateToStage5 = { navigateTo(Stage5Activity::class.java) }
                    )
                }
            }
        }
    }

    private fun navigateTo(activityClass: Class<out ComponentActivity>) {
        startActivity(Intent(this, activityClass))
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigateToStage1: () -> Unit,
    navigateToStage2: () -> Unit,
    navigateToStage3: () -> Unit,
    navigateToStage4: () -> Unit,
    navigateToStage5: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Kinna Wa / \uD803\uDD11\uD803\uDD1E\uD803\uDD15\uD803\uDD27\uD803\uDD1D \uD803\uDD16\uD803\uDD1D",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Button(
                onClick = navigateToStage1,
                modifier = Modifier
                    .height(60.dp)
                    .width(250.dp)
            ) {
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = "Learn the Letters"
                )
            }
            Button(
                onClick = navigateToStage2,
                modifier = Modifier
                    .height(60.dp)
                    .width(250.dp)
            ) {
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = "Simple Words"
                )
            }
            Button(
                onClick = navigateToStage3,
                modifier = Modifier
                    .height(60.dp)
                    .width(250.dp)
            ) {
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = "Forming Sentences"
                )
            }
            Button(
                onClick = navigateToStage4,
                modifier = Modifier
                    .height(60.dp)
                    .width(250.dp)
            ) {
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = "Reading Practice"
                )
            }
            Button(
                onClick = navigateToStage5,
                modifier = Modifier
                    .height(60.dp)
                    .width(250.dp)
            ) {
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = "Complex Sentences"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    KinnaWaTheme {
        MainScreen(
            navigateToStage1 = {},
            navigateToStage2 = {},
            navigateToStage3 = {},
            navigateToStage4 = {},
            navigateToStage5 = {}
        )
    }
}
