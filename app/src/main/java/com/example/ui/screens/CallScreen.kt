package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CallScreen(onNavigateBack: () -> Unit) {
    var isMicOn by remember { mutableStateOf(true) }
    var isCameraOn by remember { mutableStateOf(true) }
    var isSpeakerOn by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Fullscreen Video (Remote)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("ویدئوی طرف مقابل", color = Color.White)
        }

        // PIP Video (Local)
        Box(
            modifier = Modifier
                .padding(16.dp)
                .size(100.dp, 150.dp)
                .align(Alignment.TopEnd)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            Text("شما", color = Color.White)
        }

        // Call Controls
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { isMicOn = !isMicOn },
                modifier = Modifier
                    .background(if (isMicOn) Color.DarkGray else Color.White, CircleShape)
                    .padding(8.dp)
            ) {
                Icon(
                    if (isMicOn) Icons.Default.Mic else Icons.Default.MicOff,
                    contentDescription = "Mic",
                    tint = if (isMicOn) Color.White else Color.Black
                )
            }
            
            IconButton(
                onClick = { isCameraOn = !isCameraOn },
                modifier = Modifier
                    .background(if (isCameraOn) Color.DarkGray else Color.White, CircleShape)
                    .padding(8.dp)
            ) {
                Icon(
                    if (isCameraOn) Icons.Default.Videocam else Icons.Default.VideocamOff,
                    contentDescription = "Camera",
                    tint = if (isCameraOn) Color.White else Color.Black
                )
            }

            FloatingActionButton(
                onClick = onNavigateBack,
                containerColor = Color.Red,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.CallEnd, contentDescription = "End Call")
            }

            IconButton(
                onClick = { },
                modifier = Modifier
                    .background(Color.DarkGray, CircleShape)
                    .padding(8.dp)
            ) {
                Icon(Icons.Default.FlipCameraIos, contentDescription = "Flip", tint = Color.White)
            }

            IconButton(
                onClick = { isSpeakerOn = !isSpeakerOn },
                modifier = Modifier
                    .background(if (isSpeakerOn) Color.DarkGray else Color.White, CircleShape)
                    .padding(8.dp)
            ) {
                Icon(
                    if (isSpeakerOn) Icons.Default.VolumeUp else Icons.Default.VolumeOff,
                    contentDescription = "Speaker",
                    tint = if (isSpeakerOn) Color.White else Color.Black
                )
            }
        }
    }
}
