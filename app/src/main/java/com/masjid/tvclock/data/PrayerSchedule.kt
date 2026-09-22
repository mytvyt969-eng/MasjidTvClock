package com.masjid.tvclock.data

import java.time.LocalDateTime

enum class Prayer(val label: String) {
    FAJR("Fajr"),
    DHUHR("Dhuhr"),
    ASR("Asr"),
    MAGHRIB("Maghrib"),
    ISHA("Isha")
}

data class PrayerEntry(
    val prayer: Prayer,
    val adhan: LocalDateTime,
    val iqamah: LocalDateTime
)

data class DaySchedule(
    val date: LocalDateTime,
    val fajr: PrayerEntry,
    val sunrise: LocalDateTime,
    val dhuhr: PrayerEntry,
    val asr: PrayerEntry,
    val maghrib: PrayerEntry,
    val isha: PrayerEntry,
    val fajrEnd: LocalDateTime
) {
    val entries: List<PrayerEntry> get() = listOf(fajr, dhuhr, asr, maghrib, isha)
}
