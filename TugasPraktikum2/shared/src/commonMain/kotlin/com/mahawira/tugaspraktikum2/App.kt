package com.mahawira.tugaspraktikum2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun App() {
    MaterialTheme {
        val manager = remember { NewsFeedManajer() }
        var feedItems by remember { mutableStateOf(listOf<String>()) }
        val jumlahBaca by manager.jumlahBaca.collectAsState()

        LaunchedEffect(Unit) {
            runNewsFeed(manager, "Teknologi") { text, detail ->
                feedItems = feedItems + "$text\n${detail.fullKonten}"
            }
        }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Total dibaca: $jumlahBaca", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            LazyColumn {
                items(feedItems) { item ->
                    Text(item, modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
    }
}