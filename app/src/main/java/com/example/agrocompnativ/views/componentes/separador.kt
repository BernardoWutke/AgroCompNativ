package com.example.agrocompnativ.views.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun separador(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF338AAA))
            .size(height = 10.dp, width = 10.dp)
    )
}