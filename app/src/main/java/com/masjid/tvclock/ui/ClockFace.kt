package com.masjid.tvclock.ui
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import java.time.LocalTime
import kotlin.math.*
@Composable
fun ClockFace(time:LocalTime,diameter:Dp,faceColor:Color,rimColor:Color,tickColor:Color,handColor:Color,modifier:Modifier=Modifier){
    Box(modifier.size(diameter),contentAlignment=Alignment.Center){Canvas(Modifier.size(diameter)){
        val r=min(size.width,size.height)/2;val c=Offset(size.width/2,size.height/2)
        drawCircle(rimColor,r);drawCircle(faceColor,r*.94f,c)
        fun hand(a:Double,len:Float,w:Float,col:Color){drawLine(col,c,Offset(c.x+len*cos(a).toFloat(),c.y+len*sin(a).toFloat()),w,cap=androidx.compose.ui.graphics.StrokeCap.Round)}
        hand(Math.toRadians(((time.hour%12+time.minute/60f)*30-90).toDouble()),r*.48f,r*.05f,handColor)
        hand(Math.toRadians((time.minute*6-90).toDouble()),r*.68f,r*.035f,handColor)
        hand(Math.toRadians((time.second*6-90).toDouble()),r*.72f,r*.012f,Color.Red)
        drawCircle(handColor,r*.045f,c)
    }}
}