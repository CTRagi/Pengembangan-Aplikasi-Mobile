package com.mahawira.tugaspraktikum2

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.time.Duration.Companion.seconds

data class News(
    val id: Int,
    val kategori: String,
    val title: String
)

data class DetailNews(
    val id: Int,
    val fullKonten: String
)

val kategoriNews = listOf("Pendidikan", "Kesehatan", "Olahraga", "Politik", "Teknologi", "Ekonomi")

fun newsFeedFlow(): Flow<News> = flow {
    var id = 1
    while (true) {
        delay(2.seconds)
        val kategori = kategoriNews.random()
        emit(
            News(
                id = id,
                kategori = kategori,
                title = "Update terbaru seputar $kategori (#$id)"
            )
        )
        id++
    }
}

suspend fun ambilDetailNews(newsId: Int): DetailNews = withContext(Dispatchers.Default) {
    delay(0.3.seconds)
    DetailNews(
        id = newsId,
        fullKonten = "Isi lengkap berita nomor $newsId, diambil secara synchronous."
    )
}

class NewsFeedManajer {
    private val _jumlahBaca = MutableStateFlow(0)
    val jumlahBaca: StateFlow<Int> = _jumlahBaca.asStateFlow()

    fun tandaiDibaca() {
        _jumlahBaca.value++
    }
}

suspend fun runNewsFeed(manajer: NewsFeedManajer, kategoriDiminati: String, limit: Int = 4, onNewsDisplayed: (String, DetailNews) -> Unit) {
    newsFeedFlow().filter {
        it.kategori == kategoriDiminati
    }.map {
        "[${it.kategori}] ${it.title}"
    }.catch {
            e -> onNewsDisplayed("Error: ${e.message}", DetailNews(-1, ""))
    }.take(limit).collect {
            teksTampilan -> val detail = ambilDetailNews(manajer.jumlahBaca.value + 1)
        onNewsDisplayed(teksTampilan, detail)
        manajer.tandaiDibaca()
    }
}