package com.mahawira.tugaspraktikum4

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mahawira.tugaspraktikum4.ui.ProfileScreen
import com.mahawira.tugaspraktikum4.ui.theme.animatedColorScheme
import com.mahawira.tugaspraktikum4.viewmodel.ProfileViewModel
import org.jetbrains.compose.resources.painterResource
import tugaspraktikum4.shared.generated.resources.Myself

import tugaspraktikum4.shared.generated.resources.Res
import tugaspraktikum4.shared.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    val viewModel = viewModel { ProfileViewModel() }
    val uiState by viewModel.uiState.collectAsState()

    MaterialTheme(colorScheme = animatedColorScheme(uiState.isDarkMode)) {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.Myself), null)
                    Text(
                        "Compose: $greeting",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            }

            ProfileScreen(
                uiState = uiState,
                onEditClick = viewModel::startEdit,
                onSaveProfile = viewModel::saveProfile,
                onCancelEdit = viewModel::cancelEdit,
                onDarkModeChange = viewModel::setDarkMode,
                modifier = Modifier.weight(1f),
            )
        }
    }
}