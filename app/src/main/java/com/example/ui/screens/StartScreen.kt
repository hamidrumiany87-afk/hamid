package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import kotlinx.coroutines.delay

@Composable
fun StartScreen(onNavigateToMain: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    var username by remember { mutableStateOf("") }
    
    LaunchedEffect(Unit) {
        delay(1500)
        showDialog = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.hrsip_icon_1783435274140),
                contentDescription = "HR-SIP Logo",
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(32.dp))
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "HR-SIP",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "ارتباط امن و مستقیم",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("تنظیم نام کاربری") },
            text = {
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("نام شما") },
                    singleLine = true
                )
            },
            confirmButton = {
                Button(
                    onClick = { 
                        showDialog = false
                        onNavigateToMain()
                    },
                    enabled = username.isNotBlank()
                ) {
                    Text("شروع")
                }
            }
        )
    }
}
