package com.mahawira.tugaspraktikum4.viewmodel

import com.mahawira.tugaspraktikum4.data.Profile

data class ProfileUiState(
    val profile: Profile = Profile(name = "", bio = ""),
    val isEditing: Boolean = false,
    val isDarkMode: Boolean = false,
)