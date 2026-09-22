package com.masjid.tvclock.ui
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
enum class GlyphType{SUN,SUNRISE,SUNSET,MOON,MOSQUE,SPEAKER}
@Composable fun Glyph(type:GlyphType,tint:Color,size:androidx.compose.ui.unit.Dp=24.dp){Canvas(Modifier.size(size)){val r=this.size.minDimension;val c=Offset(r/2,r/2);when(type){GlyphType.SUN->drawCircle(tint,r*.25f,c);GlyphType.MOON->drawCircle(tint,r*.32f,c);GlyphType.SUNRISE,GlyphType.SUNSET->drawCircle(tint,r*.25f,c);GlyphType.MOSQUE->{drawRect(tint,Offset(r*.25f,r*.45f),androidx.compose.ui.geometry.Size(r*.5f,r*.35f));drawCircle(tint,r*.25f,Offset(r*.5f,r*.45f))};GlyphType.SPEAKER->drawRect(tint,Offset(r*.25f,r*.35f),androidx.compose.ui.geometry.Size(r*.5f,r*.3f))}}}