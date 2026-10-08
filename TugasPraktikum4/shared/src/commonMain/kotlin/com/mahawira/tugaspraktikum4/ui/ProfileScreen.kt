package com.mahawira.tugaspraktikum4.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mahawira.tugaspraktikum4.viewmodel.ProfileUiState

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    onEditClick: () -> Unit,
    onSaveProfile: (name: String, bio: String) -> Unit,
    onCancelEdit: () -> Unit,
    onDarkModeChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        DarkModeToggle(
            isDarkMode = uiState.isDarkMode,
            onCheckedChange = onDarkModeChange,
        )

        Crossfade(
            targetState = uiState.isEditing,
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
            label = "profileMode",
        ) { isEditing ->
            if (isEditing) {
                EditProfileForm(
                    initialName = uiState.profile.name,
                    initialBio = uiState.profile.bio,
                    onSave = onSaveProfile,
                    onCancel = onCancelEdit,
                )
            } else {
                ProfileCard(
                    profile = uiState.profile,
                    onEditClick = onEditClick,
                )
            }
        }
    }
}