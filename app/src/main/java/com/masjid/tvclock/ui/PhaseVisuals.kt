package com.masjid.tvclock.ui

import androidx.compose.ui.graphics.Color
import com.masjid.tvclock.logic.DayPhase

enum class Celestial { SUN, MOON, CRESCENT_LOW, NONE }

data class PhaseVisuals(
    val skyTop: Color, val skyMid: Color, val skyBottom: Color,
    val cardTint: Color, val cardBorder: Color, val accent: Color,
    val textPrimary: Color, val textSecondary: Color,
    val silhouetteColor: Color, val silhouetteGlow: Color,
    val starsVisible: Boolean, val celestial: Celestial,
    val clockFace: Color=Color(0xFFFAFAFA),
    val clockRim: Color=Color(0xFF15161B),
    val clockTicks: Color=Color(0xFF3A3A44),
    val clockHands: Color=Color(0xFF15161B)
)

object PhaseVisualsCatalog {
    private val preFajr=PhaseVisuals(Color(0xFF090B2A),Color(0xFF2A1D58),Color(0xFF62417A),Color.White.copy(.12f),Color.White.copy(.25f),Color(0xFFD9B7FF),Color.White,Color.White.copy(.72f),Color(0xFF080914),Color(0xFFB89BFF),true,Celestial.CRESCENT_LOW)
    private val morning=PhaseVisuals(Color(0xFF62B8FF),Color(0xFF9DD8FF),Color(0xFFEAF8FF),Color.White.copy(.18f),Color.White.copy(.38f),Color(0xFF146B9A),Color(0xFF10243A),Color(0xFF31536A),Color(0xFF19384B),Color.White,false,Celestial.SUN)
    private val afternoon=PhaseVisuals(Color(0xFFF3B35E),Color(0xFFFFD98B),Color(0xFFFFEED0),Color.White.copy(.20f),Color.White.copy(.40f),Color(0xFF9A5A14),Color(0xFF38200B),Color(0xFF6A4A28),Color(0xFF5C3716),Color.White.copy(.7f),false,Celestial.SUN)
    private val sunset=PhaseVisuals(Color(0xFF8E2F42),Color(0xFFE56B45),Color(0xFFFFB36B),Color.White.copy(.16f),Color.White.copy(.35f),Color(0xFFFFD19A),Color.White,Color.White.copy(.75f),Color(0xFF401622),Color(0xFFFFC07A),false,Celestial.NONE)
    private val night=PhaseVisuals(Color(0xFF050B25),Color(0xFF101C4A),Color(0xFF1A2E63),Color.White.copy(.12f),Color.White.copy(.24f),Color(0xFF8EA8FF),Color.White,Color.White.copy(.70f),Color(0xFF050817),Color(0xFF6E86D6),true,Celestial.MOON)
    fun forPhase(phase:DayPhase)=when(phase){
        DayPhase.PRE_FAJR->preFajr
        DayPhase.MORNING->morning
        DayPhase.AFTERNOON->afternoon
        DayPhase.SUNSET->sunset
        DayPhase.NIGHT->night
    }
}
