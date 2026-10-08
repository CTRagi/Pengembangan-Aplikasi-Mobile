package com.mahawira.tugaspraktikum4.data

class ProfileRepository {

    private var currentProfile = Profile(
        name = "Mahawira Athaya Fikri",
        bio = "Mahasiswa Semester 5 Teknik Informatika ITERA (Institut Teknologi Sumatera) yang sedang belajar Matakuliah Pengembangan Aplikasi Mobile.",
    )

    fun getProfile(): Profile = currentProfile

    fun saveProfile(profile: Profile) {
        currentProfile = profile
    }
}