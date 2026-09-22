package com.masjid.tvclock.logic
import com.masjid.tvclock.data.*
import java.time.*
enum class DayPhase{PRE_FAJR,MORNING,AFTERNOON,SUNSET,NIGHT}
data class ThemeState(val phase:DayPhase,val nextPrayer:Prayer,val nextPrayerLabel:String,val nextPrayerAt:LocalDateTime,val countdown:Duration,val highlightedPrayer:Prayer)
object ThemeEngine {
    fun phaseFor(p:Prayer)=when(p){Prayer.FAJR->DayPhase.PRE_FAJR;Prayer.DHUHR->DayPhase.MORNING;Prayer.ASR->DayPhase.AFTERNOON;Prayer.MAGHRIB->DayPhase.SUNSET;Prayer.ISHA->DayPhase.NIGHT}
    fun currentState(now:LocalDateTime,today:DaySchedule,tomorrow:DaySchedule):ThemeState {
        val n=today.entries.firstOrNull{it.adhan.isAfter(now)} ?: PrayerEntry(Prayer.FAJR,tomorrow.fajr.adhan,tomorrow.fajr.iqamah)
        val cd=Duration.between(now,n.adhan).coerceAtLeast(Duration.ZERO)
        return ThemeState(phaseFor(n.prayer),n.prayer,n.prayer.label,n.adhan,cd,n.prayer)
    }
}
private fun Duration.coerceAtLeast(other:Duration)=if(this<other)other else this