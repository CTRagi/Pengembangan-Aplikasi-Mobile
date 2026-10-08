package com.mahawira.tugaspraktikum4.viewmodel

import androidx.lifecycle.ViewModel
import com.mahawira.tugaspraktikum4.data.Profile
import com.mahawira.tugaspraktikum4.data.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel(
    private val repository: ProfileRepository = ProfileRepository(),
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        ProfileUiState(profile = repository.getProfile())
    )
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun startEdit() {
        _uiState.update { it.copy(isEditing = true) }
    }

    fun cancelEdit() {
        _uiState.update { it.copy(isEditing = false) }
    }

    fun saveProfile(name: String, bio: String) {
        if (name.isBlank()) return

        val updated = Profile(name = name.trim(), bio = bio.trim())
        repository.saveProfile(updated)
        _uiState.update { it.copy(profile = updated, isEditing = false) }
    }

    fun setDarkMode(enabled: Boolean) {
        _uiState.update { it.copy(isDarkMode = enabled) }
    }
}