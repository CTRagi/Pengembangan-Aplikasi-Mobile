package com.mahawira.tugaspraktikum2

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val manajer = NewsFeedManajer()

    val monitorJob = launch {
        manajer.jumlahBaca.collect { count ->
            println(">> [StateFlow] Total dibaca: $count")
        }
    }

    println("=== NEWS FEED SIMULATOR (Desktop) ===\n")

    runNewsFeed(manajer, kategoriDiminati = "Teknologi") { displayText, detail ->
        println("\n📰 $displayText")
        println("   Detail: ${detail.fullKonten}")
    }

    monitorJob.cancel()
    println("\n=== SELESAI ===")
}