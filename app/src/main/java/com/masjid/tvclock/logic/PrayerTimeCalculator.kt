package com.masjid.tvclock.logic
import com.batoulapps.adhan.Coordinates\nimport com.batoulapps.adhan.PrayerTimes\nimport com.batoulapps.adhan.data.DateComponents
import com.batoulapps.adhan.data.DateComponents
import com.masjid.tvclock.data.*
import java.time.*
import java.util.Date
object PrayerTimeCalculator {
    private val zone=ZoneId.of(MosqueConfig.TIME_ZONE_ID)
    fun schedule(date:LocalDate=LocalDate.now(zone)):DaySchedule {
        val c=Coordinates(MosqueConfig.LATITUDE,MosqueConfig.LONGITUDE)
        val p=MosqueConfig.CALCULATION_METHOD.parameters
        p.madhab=MosqueConfig.MADHAB
        val t=PrayerTimes(c,DateComponents(date.year,date.monthValue,date.dayOfMonth),p)
        val f=t.fajr.toLocalDateTime(); val sr=t.sunrise.toLocalDateTime(); val d=t.dhuhr.toLocalDateTime(); val a=t.asr.toLocalDateTime(); val m=t.maghrib.toLocalDateTime(); val i=t.isha.toLocalDateTime()
        fun iq(pr:Prayer,ad:LocalDateTime):LocalDateTime {
            if(!MosqueConfig.USE_FIXED_IQAMAH_TIMES) return ad.plusMinutes(when(pr){Prayer.FAJR->8L;Prayer.DHUHR->15L;Prayer.ASR->15L;Prayer.MAGHRIB->1L;Prayer.ISHA->5L})
            val s=when(pr){Prayer.FAJR->MosqueConfig.IQAMAH_FAJR;Prayer.DHUHR->MosqueConfig.IQAMAH_DHUHR;Prayer.ASR->MosqueConfig.IQAMAH_ASR;Prayer.MAGHRIB->MosqueConfig.IQAMAH_MAGHRIB;Prayer.ISHA->MosqueConfig.IQAMAH_ISHA}
            return LocalDateTime.of(date,LocalTime.parse(s))
        }
        return DaySchedule(LocalDateTime.of(date,LocalTime.MIDNIGHT),PrayerEntry(Prayer.FAJR,f,iq(Prayer.FAJR,f)),sr,PrayerEntry(Prayer.DHUHR,d,iq(Prayer.DHUHR,d)),PrayerEntry(Prayer.ASR,a,iq(Prayer.ASR,a)),PrayerEntry(Prayer.MAGHRIB,m,iq(Prayer.MAGHRIB,m)),PrayerEntry(Prayer.ISHA,i,iq(Prayer.ISHA,i)),sr)
    }
    private fun Date.toLocalDateTime()=toInstant().atZone(zone).toLocalDateTime()
}