package com.mahawira.tugaspraktikum2

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.browser.document

val appScope = MainScope()

fun main() {
    val manager = NewsFeedManajer()
    val output = document.getElementById("output")

    appScope.launch {
        manager.jumlahBaca.collect { count ->
            console.log("[StateFlow] Total dibaca: $count")
        }
    }

    appScope.launch {
        runNewsFeed(manager, kategoriDiminati = "Teknologi") { displayText, detail ->
            output?.append(
                document.createElement("p").apply {
                    textContent = "$displayText — ${detail.fullKonten}"
                }
            )
        }
    }
}