package com.masjid.tvclock.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.masjid.tvclock.data.*
import com.masjid.tvclock.logic.*
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun MainScreen() {
    var now by remember { mutableStateOf(LocalDateTime.now()) }
    LaunchedEffect(Unit) { while (true) { now = LocalDateTime.now(); delay(1000) } }
    val today = remember(now.toLocalDate()) { PrayerTimeCalculator.schedule(now.toLocalDate()) }
    val tomorrow = remember(now.toLocalDate()) { PrayerTimeCalculator.schedule(now.toLocalDate().plusDays(1)) }
    val state = ThemeEngine.currentState(now, today, tomorrow)
    val visuals = PhaseVisualsCatalog.forPhase(state.phase)
    val fmt = DateTimeFormatter.ofPattern("h:mm a")
    Box(Modifier.fillMaxSize()) {
        SkyBackground(visuals, Modifier.fillMaxSize())
        Column(Modifier.fillMaxSize().padding(28.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement=Arrangement.SpaceBetween) {
                Column {
                    Text(MosqueConfig.MOSQUE_NAME,color=visuals.textPrimary,fontSize=28.sp,fontWeight=FontWeight.Bold)
                    Text(MosqueConfig.MOSQUE_LOCATION,color=visuals.textSecondary,fontSize=17.sp)
                }
                Column(horizontalAlignment=Alignment.End) {
                    Text(HijriDateProvider.gregorianFormatted(now.toLocalDate()),color=visuals.textPrimary,fontSize=20.sp)
                    Text(HijriDateProvider.formatted(now.toLocalDate()),color=visuals.textSecondary,fontSize=16.sp)
                }
            }
            Spacer(Modifier.height(16.dp))
            Row(Modifier.fillMaxWidth().weight(1f),verticalAlignment=Alignment.CenterVertically) {
                GlassBox(visuals,Modifier.weight(1f)) {
                    Text("Jummah",color=visuals.textPrimary,fontSize=24.sp,fontWeight=FontWeight.Bold)
                    Text("Adhan  " + MosqueConfig.JUMMAH_ADHAN,color=visuals.textSecondary,fontSize=16.sp)
                    Text("Jamaat " + MosqueConfig.JUMMAH_JAMAAT,color=visuals.textSecondary,fontSize=16.sp)
                }
                Spacer(Modifier.width(16.dp))
                GlassBox(visuals,Modifier.weight(1.6f)) {
                    Row(verticalAlignment=Alignment.CenterVertically) {
                        ClockFace(now.toLocalTime(),150.dp,visuals.clockFace,visuals.clockRim,visuals.clockTicks,visuals.clockHands)
                        Spacer(Modifier.width(18.dp))
                        Column {
                            Text("NEXT PRAYER",color=visuals.textSecondary,fontSize=14.sp)
                            Text(state.nextPrayerLabel,color=visuals.textPrimary,fontSize=30.sp,fontWeight=FontWeight.Bold)
                            Text("%02d:%02d:%02d".format(state.countdown.toHours(),state.countdown.toMinutesPart(),state.countdown.toSecondsPart()),color=visuals.accent,fontSize=30.sp,fontWeight=FontWeight.Bold)
                        }
                    }
                }
                Spacer(Modifier.width(16.dp))
                GlassBox(visuals,Modifier.weight(1f)) {
                    Text("Fajr Start  " + today.fajr.adhan.format(fmt),color=visuals.textPrimary,fontSize=17.sp)
                    Text("Fajr End    " + today.fajrEnd.format(fmt),color=visuals.textPrimary,fontSize=17.sp)
                    Text("Sunrise     " + today.sunrise.format(fmt),color=visuals.textSecondary,fontSize=16.sp)
                    Text("Sunset      " + today.maghrib.adhan.format(fmt),color=visuals.textSecondary,fontSize=16.sp)
                }
            }
            Spacer(Modifier.height(14.dp))
            Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(12.dp)) {
                today.entries.forEach { entry ->
                    PrayerCard({ Glyph(prayerGlyph(entry.prayer),visuals.textPrimary,20.dp) },entry.prayer.label,entry.adhan,entry.iqamah,entry.prayer==state.highlightedPrayer,visuals,Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun GlassBox(visuals: PhaseVisuals,modifier: Modifier=Modifier,content: @Composable ColumnScope.()->Unit) {
    Column(modifier.fillMaxHeight(),verticalArrangement=Arrangement.Center,content=content)
}

fun prayerGlyph(prayer: Prayer)=when(prayer){
    Prayer.FAJR->GlyphType.MOON
    Prayer.DHUHR->GlyphType.SUN
    Prayer.ASR->GlyphType.SUN
    Prayer.MAGHRIB->GlyphType.SUNSET
    Prayer.ISHA->GlyphType.MOON
}
