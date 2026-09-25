package com.mahawira.tugaspraktikum2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform