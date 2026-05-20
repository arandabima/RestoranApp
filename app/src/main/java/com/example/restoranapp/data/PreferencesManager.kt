package com.example.restoranapp.data

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("restoran_prefs", Context.MODE_PRIVATE)

    companion object {
        const val KEY_NAMA = "nama_restoran"
        const val KEY_ALAMAT = "alamat"
        const val KEY_DESKRIPSI = "deskripsi"
        const val KEY_JAM_BUKA = "jam_buka"
        const val KEY_DARK_MODE = "dark_mode"
    }

    init {
        if (!prefs.contains(KEY_NAMA)) {
            saveProfile(
                nama = "Warung Nusantara",
                alamat = "Jl. Malioboro No. 42, Yogyakarta",
                deskripsi = "Menyajikan cita rasa masakan Indonesia sejak 1995.",
                jamBuka = "08.00 – 22.00 WIB"
            )
        }
    }

    fun getNama() = prefs.getString(KEY_NAMA, "") ?: ""
    fun getAlamat() = prefs.getString(KEY_ALAMAT, "") ?: ""
    fun getDeskripsi() = prefs.getString(KEY_DESKRIPSI, "") ?: ""
    fun getJamBuka() = prefs.getString(KEY_JAM_BUKA, "") ?: ""
    fun isDarkMode() = prefs.getBoolean(KEY_DARK_MODE, false)

    fun saveProfile(nama: String, alamat: String, deskripsi: String, jamBuka: String) {
        prefs.edit().apply {
            putString(KEY_NAMA, nama)
            putString(KEY_ALAMAT, alamat)
            putString(KEY_DESKRIPSI, deskripsi)
            putString(KEY_JAM_BUKA, jamBuka)
            apply()
        }
    }

    fun toggleDarkMode(): Boolean {
        val newVal = !isDarkMode()
        prefs.edit().putBoolean(KEY_DARK_MODE, newVal).apply()
        return newVal
    }
}