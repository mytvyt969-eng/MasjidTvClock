package com.masjid.tvclock.logic
import com.masjid.tvclock.data.MosqueConfig
import java.time.LocalDate
import java.time.chrono.HijrahDate
import java.time.format.TextStyle
import java.util.Locale
object HijriDateProvider {
    private val months=listOf("Muharram","Safar","Rabi' al-Awwal","Rabi' al-Thani","Jumada al-Awwal","Jumada al-Thani","Rajab","Sha'ban","Ramadan","Shawwal","Dhu al-Qi'dah","Dhu al-Hijjah")
    fun formatted(date:LocalDate=LocalDate.now()):String {
        val h=HijrahDate.from(date.plusDays(MosqueConfig.HIJRI_DAY_ADJUSTMENT.toLong()))
        val d=h.get(java.time.temporal.ChronoField.DAY_OF_MONTH)
        val m=h.get(java.time.temporal.ChronoField.MONTH_OF_YEAR)
        val y=h.get(java.time.temporal.ChronoField.YEAR)
        return "%02d %s %d AH".format(d,months[m-1],y)
    }
    fun gregorianFormatted(date:LocalDate=LocalDate.now()):String {
        val dow=date.dayOfWeek.getDisplayName(TextStyle.SHORT,Locale.ENGLISH)
        val mon=date.month.getDisplayName(TextStyle.SHORT,Locale.ENGLISH)
        return "$dow, %02d %s %d".format(date.dayOfMonth,mon,date.year)
    }
}