package com.masjid.tvclock

import com.masjid.tvclock.data.Prayer
import com.masjid.tvclock.logic.DayPhase
import com.masjid.tvclock.logic.ThemeEngine
import org.junit.Assert.assertEquals
import org.junit.Test

class ThemeEngineTest {
    @Test fun eachPrayerMapsToItsOwnPhase() {
        assertEquals(DayPhase.PRE_FAJR, ThemeEngine.phaseFor(Prayer.FAJR))
        assertEquals(DayPhase.MORNING, ThemeEngine.phaseFor(Prayer.DHUHR))
        assertEquals(DayPhase.AFTERNOON, ThemeEngine.phaseFor(Prayer.ASR))
        assertEquals(DayPhase.SUNSET, ThemeEngine.phaseFor(Prayer.MAGHRIB))
        assertEquals(DayPhase.NIGHT, ThemeEngine.phaseFor(Prayer.ISHA))
    }
}