package com.masjid.tvclock.logic

import com.batoulapps.adhan.Coordinates
import com.batoulapps.adhan.CalculationParameters
import com.batoulapps.adhan.PrayerTimes as AdhanPrayerTimes
import com.batoulapps.adhan.data.DateComponents
import com.masjid.tvclock.data.DaySchedule
import com.masjid.tvclock.data.MosqueConfig
import com.masjid.tvclock.data.Prayer
import com.masjid.tvclock.data.PrayerEntry
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.Date

object PrayerTimeCalculator {
    private val zone=ZoneId.of(MosqueConfig.TIME_ZONE_ID)
    fun schedule(date:LocalDate=LocalDate.now(zone)):DaySchedule {
        val coordinates=Coordinates(MosqueConfig.LATITUDE,MosqueConfig.LONGITUDE)
        val params:CalculationParameters=MosqueConfig.CALCULATION_METHOD.parameters
        params.madhab=MosqueConfig.MADHAB
        val times=AdhanPrayerTimes(coordinates,DateComponents(date.year,date.monthValue,date.dayOfMonth),params)
        val f=times.fajr.toLocalDateTime()
        val sr=times.sunrise.toLocalDateTime()
        val d=times.dhuhr.toLocalDateTime()
        val a=times.asr.toLocalDateTime()
        val m=times.maghrib.toLocalDateTime()
        val i=times.isha.toLocalDateTime()
        fun iq(pr:Prayer,ad:LocalDateTime):LocalDateTime {
            if(!MosqueConfig.USE_FIXED_IQAMAH_TIMES) {
                val off=when(pr){Prayer.FAJR->8L;Prayer.DHUHR->15L;Prayer.ASR->15L;Prayer.MAGHRIB->1L;Prayer.ISHA->5L}
                return ad.plusMinutes(off)
            }
            val s=when(pr){Prayer.FAJR->MosqueConfig.IQAMAH_FAJR;Prayer.DHUHR->MosqueConfig.IQAMAH_DHUHR;Prayer.ASR->MosqueConfig.IQAMAH_ASR;Prayer.MAGHRIB->MosqueConfig.IQAMAH_MAGHRIB;Prayer.ISHA->MosqueConfig.IQAMAH_ISHA}
            return LocalDateTime.of(date,LocalTime.parse(s))
        }
        return DaySchedule(
            LocalDateTime.of(date,LocalTime.MIDNIGHT),
            PrayerEntry(Prayer.FAJR,f,iq(Prayer.FAJR,f)),sr,
            PrayerEntry(Prayer.DHUHR,d,iq(Prayer.DHUHR,d)),
            PrayerEntry(Prayer.ASR,a,iq(Prayer.ASR,a)),
            PrayerEntry(Prayer.MAGHRIB,m,iq(Prayer.MAGHRIB,m)),
            PrayerEntry(Prayer.ISHA,i,iq(Prayer.ISHA,i)),sr)
    }
    private fun Date.toLocalDateTime():LocalDateTime=toInstant().atZone(zone).toLocalDateTime()
}