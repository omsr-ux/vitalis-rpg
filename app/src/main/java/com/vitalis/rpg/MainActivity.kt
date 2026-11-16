package com.vitalis.rpg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VitalisRpgAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF0B0E14)
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun VitalisRpgAppTheme(content: @Composable () -> Unit) {
    MaterialColorScheme(content = content)
}

@Composable
fun MaterialColorScheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Color(0xFF00F59B),
            secondary = Color(0xFFFFB800),
            background = Color(0xFF0B0E14),
            surface = Color(0xFF151922)
        ),
        content = content
    )
}

@Composable
fun MainScreen() {
    var recoveryScore by remember { mutableStateOf(92) }
    var dayStrain by remember { mutableStateOf(14.2) }
    var isArabic by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // شريط العنوان وتبديل اللغة
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isArabic) "فايناليس آر بي جي (إيسكانور)" else "VITALIS RPG (Escanor)",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = Bold
            )
            Button(onClick = { isArabic = !isArabic }) {
                Text(text = if (isArabic) "EN" else "العربية")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // بطاقة شخصية الأنمي (Escanor The One State)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF151922))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "☀️ [THE ONE SUNSHINE STATE] ☀️",
                    color = Color(0xFFFFB800),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (isArabic) "المستوى: 42 | القوة (STR): 98" else "Level: 42 | STR: 98",
                    color = Color.White,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                LinearProgressIndicator(
                    progress = { recoveryScore / 100f },
                    modifier = Modifier.fillMaxWidth().height(8.dp),
                    color = Color(0xFF00F59B),
                )
                Text(
                    text = if (isArabic) "نقاط التشافي والصحة (HP): $recoveryScore%" else "Recovery & HP: $recoveryScore%",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // مؤشرات WHOOP / Zepp
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MetricCard(title = if (isArabic) "المجهود" else "Strain", value = "$dayStrain")
            MetricCard(title = if (isArabic) "التشافي" else "Recovery", value = "$recoveryScore%")
        }
    }
}

@Composable
fun MetricCard(title: String, value: String) {
    Card(
        modifier = Modifier.width(150.dp).height(100.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF151922))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }
    }
}
