package com.mahawira.tugaspraktikum3

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform