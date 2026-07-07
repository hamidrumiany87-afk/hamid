package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onNavigateToCall: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToQRScan: () -> Unit,
    onNavigateToQRShow: () -> Unit
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("مخاطبین", "تماس‌ها")

    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    Brush.linearGradient(
                                        listOf(TrustBlue, VibrantOrange)
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Call, contentDescription = "Logo", tint = Color.White)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            "HR-SIP",
                            fontWeight = FontWeight.Bold,
                            color = TextPrimaryLight
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToQRScan) {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = TextSecondaryLight)
                    }
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = TextSecondaryLight)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SurfaceLight.copy(alpha = 0.8f)
                )
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundLight)
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = onNavigateToQRShow,
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SurfaceLight,
                            contentColor = TextPrimaryLight
                        ),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
                    ) {
                        Icon(Icons.Default.QrCode, contentDescription = "Show QR")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("نمایش QR", fontWeight = FontWeight.Bold)
                    }
                    
                    Button(
                        onClick = onNavigateToQRScan,
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TextPrimaryLight,
                            contentColor = Color.White
                        ),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                    ) {
                        Icon(Icons.Default.QrCodeScanner, contentDescription = "Scan Contact")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("اسکن مخاطب", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = SurfaceLight,
                contentColor = TrustBlue
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { 
                            Text(
                                title, 
                                fontWeight = FontWeight.SemiBold,
                                color = if (selectedTabIndex == index) TrustBlue else TextSecondaryLight
                            ) 
                        }
                    )
                }
            }
            if (selectedTabIndex == 0) {
                ContactsList(onCall = onNavigateToCall)
            } else {
                CallHistoryList()
            }
        }
    }
}

@Composable
fun ContactsList(onCall: () -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            ContactItem(
                name = "علی رضایی",
                ip = "fe80::4a:9bff:fe32",
                avatarLetter = "ع",
                isOnline = true,
                onCall = onCall
            )
            Spacer(modifier = Modifier.height(16.dp))
            ContactItem(
                name = "سارا احمدی",
                ip = "fe80::1c:22ff:ea11",
                avatarLetter = "س",
                isOnline = false,
                onCall = onCall
            )
            Spacer(modifier = Modifier.height(16.dp))
            ContactItem(
                name = "مدیریت شبکه",
                ip = "متصل در LAN",
                avatarLetter = "م",
                isOnline = true,
                isSpecial = true,
                onCall = onCall
            )
            Spacer(modifier = Modifier.height(24.dp))
            QuickConnectBadge()
        }
    }
}

@Composable
fun ContactItem(
    name: String, 
    ip: String, 
    avatarLetter: String, 
    isOnline: Boolean, 
    isSpecial: Boolean = false,
    onCall: () -> Unit
) {
    val opacity = if (isOnline) 1f else 0.7f
    val avatarBg = if (isSpecial) VibrantOrange.copy(alpha = 0.1f) else AvatarBgLight
    val avatarColor = if (isSpecial) VibrantOrange else TextSecondaryLight

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onCall),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceLight.copy(alpha = opacity)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(avatarBg),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        avatarLetter,
                        color = avatarColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .align(Alignment.BottomEnd)
                        .clip(CircleShape)
                        .background(if (isOnline) OnlineGreen else BorderLight)
                        .border(2.dp, SurfaceLight, CircleShape)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    name, 
                    fontWeight = FontWeight.SemiBold, 
                    color = TextPrimaryLight
                )
                if (isSpecial) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .background(TrustBlue.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text("P2P", fontSize = 10.sp, color = TrustBlue)
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(ip, fontSize = 12.sp, color = TextSecondaryLight)
                    }
                } else {
                    Text(ip, fontSize = 12.sp, color = TextSecondaryLight, style = MaterialTheme.typography.labelSmall)
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = onCall,
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        if (isSpecial) VibrantOrange.copy(alpha = 0.1f) else TrustBlue.copy(alpha = 0.1f), 
                        CircleShape
                    )
            ) {
                Icon(
                    if (isSpecial) Icons.Default.Videocam else Icons.Default.Call,
                    contentDescription = "Call",
                    tint = if (isSpecial) VibrantOrange else TrustBlue
                )
            }
        }
    }
}

@Composable
fun QuickConnectBadge() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Brush.horizontalGradient(listOf(TrustBlue, TrustBlueDark)))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    "شناسه شبکه شما",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "hrsip-77ax-229q",
                    fontSize = 14.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.QrCode, contentDescription = "QR", tint = Color.White)
            }
        }
    }
}

@Composable
fun CallHistoryList() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("تاریخچه خالی است", color = TextSecondaryLight)
    }
}
