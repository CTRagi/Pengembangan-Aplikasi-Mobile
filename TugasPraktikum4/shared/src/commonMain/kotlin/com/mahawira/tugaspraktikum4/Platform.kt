package com.mahawira.tugaspraktikum4

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform