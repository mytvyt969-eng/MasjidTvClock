package com.masjid.tvclock.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

@Composable
fun SkyBackground(visuals:PhaseVisuals,modifier:Modifier=Modifier){
    val stars=remember{val r=Random(42);List(90){Triple(r.nextFloat(),r.nextFloat()*.62f,r.nextFloat()*1.6f+.6f)}}
    Canvas(modifier.fillMaxSize()){
        val w=size.width;val h=size.height
        drawRect(brush=Brush.verticalGradient(listOf(visuals.skyTop,visuals.skyMid,visuals.skyBottom),0f,h))
        if(visuals.starsVisible)stars.forEach{(x,y,r)->drawCircle(Color.White.copy(alpha=.55f+r/4f),r,Offset(x*w,y*h))}
        when(visuals.celestial){
            Celestial.SUN->drawCircle(Color(0xFFFFF3D0),46f,Offset(w*.82f,h*.20f))
            Celestial.MOON->{val c=Offset(w*.83f,h*.16f);drawCircle(Color(0xFFF5F2E8),40f,c);drawCircle(visuals.skyTop,40f,c+Offset(16f,-10f))}
            Celestial.CRESCENT_LOW->{val c=Offset(w*.78f,h*.30f);drawCircle(Color(0xFFF7EFFF),32f,c);drawCircle(visuals.skyMid,32f,c+Offset(13f,-8f))}
            Celestial.NONE->{}
        }
        val base=h*.86f
        drawRect(visuals.silhouetteColor,Offset(0f,base),androidx.compose.ui.geometry.Size(w,h-base))
        fun minaret(cx:Float,mh:Float,mw:Float){val top=base-mh;drawRect(visuals.silhouetteColor,Offset(cx-mw/2,top),androidx.compose.ui.geometry.Size(mw,mh));drawCircle(visuals.silhouetteColor,mw*.7f,Offset(cx,top-mw*.4f))}
        minaret(w*.26f,h*.30f,w*.018f);minaret(w*.74f,h*.30f,w*.018f)
        drawOval(visuals.silhouetteColor,Offset(w*.40f,base-h*.20f),androidx.compose.ui.geometry.Size(w*.20f,h*.20f))
    }
}
