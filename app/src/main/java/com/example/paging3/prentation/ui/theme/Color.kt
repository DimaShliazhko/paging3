package com.example.paging3.prentation.ui.theme


import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color


val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val HeartRed = Color(0xFFFF4444)
val DownloadGray = Color(0xFFBBB8B8)

val Colors.topBarContentColor :Color
get() = if(isLight) Color.White else Color.LightGray

val Colors.topBarBackgroundColor :Color
    get() = if(isLight) Purple200 else Color.Black