package com.masjid.tvclock.data
import com.batoulapps.adhan.CalculationMethod
import com.batoulapps.adhan.Madhab
object MosqueConfig {
    const val MOSQUE_NAME="Jama Masjid KODWATAND, Lalpania"
    const val MOSQUE_LOCATION="Bokaro, Jharkhand, India"
    const val LATITUDE=23.6693
    const val LONGITUDE=86.1511
    const val TIME_ZONE_ID="Asia/Kolkata"
    val CALCULATION_METHOD=CalculationMethod.KARACHI
    val MADHAB=Madhab.HANAFI
    const val USE_FIXED_IQAMAH_TIMES=true
    const val IQAMAH_FAJR="05:55"
    const val IQAMAH_DHUHR="12:30"
    const val IQAMAH_ASR="15:45"
    const val IQAMAH_MAGHRIB="17:50"
    const val IQAMAH_ISHA="18:55"
    const val IQAMAH_OFFSET_FAJR=8
    const val IQAMAH_OFFSET_DHUHR=15
    const val IQAMAH_OFFSET_ASR=15
    const val IQAMAH_OFFSET_MAGHRIB=1
    const val IQAMAH_OFFSET_ISHA=5
    const val JUMMAH_ADHAN="13:15"
    const val JUMMAH_JAMAAT="13:30"
    val FOOTER_MESSAGES=listOf("Welcome to $MOSQUE_NAME","May Allah accept our prayers","Stay connected with our community")
    const val HIJRI_DAY_ADJUSTMENT=0
}