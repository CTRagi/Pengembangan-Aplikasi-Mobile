package com.mahawira.tugaspraktikum3

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TugasPraktikum3",
    ) {
        App()
    }
}