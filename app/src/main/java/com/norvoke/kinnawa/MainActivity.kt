package com.norvoke.kinnawa

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
import androidx.compose.ui.platform.LocalConfiguration
import com.norvoke.kinnawa.ui.theme.KinnaWaTheme

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
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT

    Box(modifier = modifier.fillMaxSize()) {
        if (isPortrait) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeaderTexts()
                Buttons(
                    navigateToStage1 = navigateToStage1,
                    navigateToStage2 = navigateToStage2,
                    navigateToStage3 = navigateToStage3,
                    navigateToStage4 = navigateToStage4,
                    navigateToStage5 = navigateToStage5
                )
            }
        } else {
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HeaderTexts()
                Buttons(
                    navigateToStage1 = navigateToStage1,
                    navigateToStage2 = navigateToStage2,
                    navigateToStage3 = navigateToStage3,
                    navigateToStage4 = navigateToStage4,
                    navigateToStage5 = navigateToStage5
                )
            }
        }
    }
}

@Composable
fun HeaderTexts() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "\uD803\uDD11\uD803\uDD1E\uD803\uDD15\uD803\uDD27\uD803\uDD1D \uD803\uDD16\uD803\uDD1D \uD803\uDD10\uD803\uDD1E\uD803\uDD25\uD803\uDD11\uD803\uDD21",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Text(
            text = "Kinna Wa Learn",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 10.dp)
        )
    }
}

@Composable
fun Buttons(
    navigateToStage1: () -> Unit,
    navigateToStage2: () -> Unit,
    navigateToStage3: () -> Unit,
    navigateToStage4: () -> Unit,
    navigateToStage5: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Button(
            onClick = navigateToStage1,
            modifier = Modifier
                .height(60.dp)
                .width(350.dp)
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = "Letters \uD83C\uDD71\uFE0F \uD803\uDD07\uD803\uDD25\uD803\uDD21\uD803\uDD0C\uD803\uDD1F\uD803\uDD09\uD803\uDD22"
            )
        }
        Button(
            onClick = navigateToStage2,
            modifier = Modifier
                .height(60.dp)
                .width(350.dp)
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = "Vowels \uD83C\uDD70\uFE0F  \uD803\uDD00\uD803\uDD1E\uD803\uDD09\uD803\uDD21\uD803\uDD0C\uD803\uDD22"
            )
        }
        Button(
            onClick = navigateToStage3,
            modifier = Modifier
                .height(60.dp)
                .width(350.dp)
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = "Write Letters \uD803\uDD07\uD803\uDD25\uD803\uDD21\uD803\uDD0C\uD803\uDD1F\uD803\uDD09\uD803\uDD22 \uD803\uDD13\uD803\uDD20\uD803\uDD11\uD803\uDD1D\uD803\uDD24 "
            )
        }
        Button(
            onClick = navigateToStage4,
            modifier = Modifier
                .height(60.dp)
                .width(350.dp)
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = "Write Vowels \uD803\uDD00\uD803\uDD1E\uD803\uDD09\uD803\uDD21\uD803\uDD0C\uD803\uDD22 \uD803\uDD13\uD803\uDD20\uD803\uDD11\uD803\uDD1D\uD803\uDD24 "
            )
        }
        Button(
            onClick = navigateToStage5,
            modifier = Modifier
                .height(60.dp)
                .width(350.dp)
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = "Write Words \uD803\uDD13\uD803\uDD20\uD803\uDD11\uD803\uDD1D\uD803\uDD24 \uD803\uDD13\uD803\uDD21\uD803\uDD09\uD803\uDD0E\uD803\uDD21\uD803\uDD25"
            )
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
                    style = MaterialTheme.typography.displaySmall,
                    text = "⬅\uFE0F"
                )
            }
        }
    }
}