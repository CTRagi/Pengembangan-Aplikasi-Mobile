package com.mahawira.tugaspraktikum2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewsFeedViewModel: ViewModel() {
    private val manajer = NewsFeedManajer()
    val jumlahBaca: StateFlow<Int> = manajer.jumlahBaca

    private val _isiFeed = MutableStateFlow<List<String>>(emptyList())
    val isiFeed: StateFlow<List<String>> = _isiFeed.asStateFlow()

    fun mulaiFeed(kategoriDiminati: String = "Pendidikan") {
        viewModelScope.launch {
            runNewsFeed(manajer, kategoriDiminati) {
                    teksTampilan, detail -> _isiFeed.value = _isiFeed.value + "$teksTampilan\n${detail.fullKonten}"
            }
        }
    }
}