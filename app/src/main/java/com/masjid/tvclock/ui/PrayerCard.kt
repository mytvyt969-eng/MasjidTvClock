package com.masjid.tvclock.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val timeFmt=DateTimeFormatter.ofPattern("h:mm a")

@Composable
fun PrayerCard(icon:@Composable()->Unit,name:String,adhan:LocalDateTime,iqamah:LocalDateTime,highlighted:Boolean,visuals:PhaseVisuals,modifier:Modifier=Modifier){
    val bg by animateColorAsState(if(highlighted) visuals.accent.copy(alpha=.22f) else visuals.cardTint,tween(600),label="bg")
    val bd by animateColorAsState(if(highlighted) visuals.accent else visuals.cardBorder,tween(600),label="bd")
    Column(modifier.clip(RoundedCornerShape(18.dp)).background(bg).border(if(highlighted)2.dp else 1.dp,bd,RoundedCornerShape(18.dp)).padding(14.dp)){
        Row(verticalAlignment=Alignment.CenterVertically){icon();Spacer(Modifier.width(8.dp));Text(name,color=visuals.textPrimary,fontSize=21.sp,fontWeight=FontWeight.SemiBold)}
        Spacer(Modifier.height(7.dp));Text("Adhan",color=visuals.textSecondary,fontSize=12.sp);Text(adhan.format(timeFmt),color=visuals.textPrimary,fontSize=18.sp,fontWeight=FontWeight.Bold)
        Spacer(Modifier.height(5.dp));Text("Iqamah",color=visuals.textSecondary,fontSize=12.sp);Text(iqamah.format(timeFmt),color=visuals.textPrimary,fontSize=18.sp,fontWeight=FontWeight.Bold)
    }
}
