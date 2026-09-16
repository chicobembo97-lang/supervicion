package com.example.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AltRoute
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DayPerformance
import com.example.data.Gestor
import com.example.data.SampleData
import com.example.ui.components.CustomLinearProgress
import com.example.ui.components.GocTopBar
import com.example.ui.theme.BrandBlueBorder
import com.example.ui.theme.BrandBlueInteractive
import com.example.ui.theme.BrandBlueLight
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.EmeraldBorder
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldTarget
import com.example.ui.theme.RedBorder
import com.example.ui.theme.RedDeficit
import com.example.ui.theme.RedLight
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceCanvas
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceSubtle
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary
import com.example.ui.viewmodel.AppNavTab
import com.example.ui.viewmodel.UiState

@Composable
fun SemanalScreen(
    state: UiState,
    onNavigate: (AppNavTab) -> Unit,
    onDaySelect: (Int) -> Unit,
    onToggleGestorAssignment: (String) -> Unit,
    onSavePlanning: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceCanvas)
    ) {
        GocTopBar(
            title = "Plan Semanal",
            onProfileClick = { onNavigate(AppNavTab.PORTAL) }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Days of the Week Strip
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val days = listOf(
                        Triple("LUN", 14, true),
                        Triple("MAR", 15, true),
                        Triple("MIÉ", 16, true),
                        Triple("JUE", 17, false),
                        Triple("VIE", 18, false),
                        Triple("SÁB", 19, false)
                    )

                    days.forEach { (name, dayNum, _) ->
                        val isSelected = state.selectedDayNumber == dayNum
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clip(RoundedCornerShape(14.dp))
                                .background(if (isSelected) BrandBlueInteractive else Color.White)
                                .border(
                                    1.dp,
                                    if (isSelected) BrandBlueInteractive else SurfaceBorder,
                                    RoundedCornerShape(14.dp)
                                )
                                .clickable { onDaySelect(dayNum) }
                                .padding(horizontal = 10.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = name,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) Color.White else TextSecondary
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "$dayNum",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Black,
                                color = if (isSelected) Color.White else DeepNavy
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Box(
                                modifier = Modifier
                                    .size(5.dp)
                                    .clip(CircleShape)
                                    .background(if (isSelected) Color.White else TextTertiary)
                            )
                        }
                    }
                }
            }

            // Centro de Cobranza Card
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(38.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(BrandBlueLight),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.LocationOn,
                                        contentDescription = null,
                                        tint = BrandBlueInteractive,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }

                                Column {
                                    Text(
                                        text = "CENTRO DE COBRANZA",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = TextTertiary,
                                        letterSpacing = 0.8.sp
                                    )
                                    Text(
                                        text = "GCC: Zacualtipan",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Black,
                                        color = DeepNavy
                                    )
                                }
                            }

                            IconButton(
                                onClick = { },
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(CircleShape)
                                    .background(BrandBlueLight)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.SwapHoriz,
                                    contentDescription = "Cambiar Centro",
                                    tint = BrandBlueInteractive,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = "Zona Operativa",
                                    fontSize = 11.sp,
                                    color = TextSecondary
                                )
                                Text(
                                    text = "Sierra Alta • Sector 4",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = DeepNavy
                                )
                            }

                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "Gestores en turno",
                                    fontSize = 11.sp,
                                    color = TextSecondary
                                )
                                Text(
                                    text = "${state.activeGestoresCount} activos hoy",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = BrandBlueInteractive
                                )
                            }
                        }
                    }
                }
            }

            // Asignación de Gestores
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AltRoute,
                            contentDescription = null,
                            tint = BrandBlueInteractive,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Asignación de Gestores",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy
                        )
                    }

                    Text(
                        text = "Día 1 • Lunes",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextSecondary
                    )
                }
            }

            // Gestor Assignment Cards
            items(state.gestores.size) { index ->
                val gestor = state.gestores[index]
                GestorAssignmentCard(
                    gestor = gestor,
                    onToggleAssignment = { onToggleGestorAssignment(gestor.id) }
                )
            }

            // Resumen Semanal GCC
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Resumen Semanal GCC",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = DeepNavy
                    )

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(BrandBlueLight)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "Semana ${state.weeklySummary.weekNumber}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = BrandBlueInteractive
                        )
                    }
                }
            }

            // 3 Vertical Metric Cards (Meta Semanal, Hoy voy por, Me falta)
            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    // Meta Semanal Card
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "META SEMANAL",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextSecondary,
                                    letterSpacing = 0.5.sp
                                )
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(BrandBlueLight)
                                        .padding(horizontal = 8.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "100% Cuota",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = BrandBlueInteractive
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Text(
                                    text = SampleData.formatCurrency(state.weeklySummary.weeklyTarget),
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Black,
                                    color = DeepNavy
                                )
                                Text(
                                    text = "Objetivo GCC",
                                    fontSize = 12.sp,
                                    color = TextSecondary
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            CustomLinearProgress(progress = 1.0f, color = BrandBlueInteractive, height = 6)
                        }
                    }

                    // Hoy voy por Card
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "HOY VOY POR",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = EmeraldDark,
                                    letterSpacing = 0.5.sp
                                )
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(EmeraldLight)
                                        .border(1.dp, EmeraldBorder, RoundedCornerShape(8.dp))
                                        .padding(horizontal = 8.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "${state.weeklySummary.percentAchieved}% Logrado",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = EmeraldDark
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Text(
                                    text = SampleData.formatCurrency(state.weeklySummary.currentAmount),
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Black,
                                    color = EmeraldDark
                                )
                                Text(
                                    text = "↗ ${state.weeklySummary.expectedPacing}",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = EmeraldDark
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            CustomLinearProgress(
                                progress = state.weeklySummary.percentAchieved / 100f,
                                color = EmeraldTarget,
                                height = 6
                            )
                        }
                    }

                    // Me falta Card
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "ME FALTA",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = RedDeficit,
                                    letterSpacing = 0.5.sp
                                )
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(RedLight)
                                        .border(1.dp, RedBorder, RoundedCornerShape(8.dp))
                                        .padding(horizontal = 8.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "39.2% Restante",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = RedDeficit
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Text(
                                    text = SampleData.formatCurrency(state.weeklySummary.deficitAmount),
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Black,
                                    color = RedDeficit
                                )
                                Text(
                                    text = "${state.weeklySummary.remainingWorkingDays} días hábiles",
                                    fontSize = 12.sp,
                                    color = TextSecondary
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            CustomLinearProgress(
                                progress = (state.weeklySummary.deficitAmount / state.weeklySummary.weeklyTarget).toFloat(),
                                color = RedDeficit,
                                height = 6
                            )
                        }
                    }
                }
            }

            // Section: Ritmo Semanal vs Meta Bar Chart
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Ritmo Semanal vs Meta",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = DeepNavy
                            )
                            Text(
                                text = state.weeklySummary.dailyAverage,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextSecondary
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Custom Weekly Bar Chart
                        WeeklyBarChart(performances = state.weeklySummary.dailyPerformances)
                    }
                }
            }

            // Action Buttons
            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(
                        onClick = onSavePlanning,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("guardar_planificacion_button"),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BrandBlueInteractive,
                            contentColor = Color.White
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "Guardar Planificación del Día",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = SurfaceSubtle,
                            contentColor = DeepNavy
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Visibility,
                                contentDescription = null,
                                tint = TextSecondary,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "Ver histórico completo Zacualtipan",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = DeepNavy
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun GestorAssignmentCard(
    gestor: Gestor,
    onToggleAssignment: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    // Badge circle "G1"
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(BrandBlueLight),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "G${gestor.number}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = BrandBlueInteractive
                        )
                    }

                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(BrandBlueLight)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "Gestor ${gestor.number}",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = BrandBlueInteractive
                                )
                            }
                            Text(
                                text = gestor.id,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Medium,
                                color = TextSecondary
                            )
                        }

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = gestor.fullName,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy
                        )
                    }
                }

                // Checkbox button
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (gestor.isAssignedToday) BrandBlueLight else SurfaceSubtle)
                        .border(
                            1.dp,
                            if (gestor.isAssignedToday) BrandBlueBorder else SurfaceBorder,
                            RoundedCornerShape(8.dp)
                        )
                        .clickable { onToggleAssignment() }
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                        .testTag("gestor_assign_${gestor.id}")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(18.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(if (gestor.isAssignedToday) BrandBlueInteractive else Color.White)
                                .border(1.dp, if (gestor.isAssignedToday) BrandBlueInteractive else TextTertiary, RoundedCornerShape(4.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            if (gestor.isAssignedToday) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }
                        Text(
                            text = if (gestor.isAssignedToday) "Asignado" else "Pendiente",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (gestor.isAssignedToday) BrandBlueInteractive else TextSecondary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Sub-bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(SurfaceSubtle)
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AltRoute,
                            contentDescription = null,
                            tint = BrandBlueInteractive,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = gestor.routeName,
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                    }

                    val statusColor = when (gestor.confirmationStatus) {
                        "Confirmado" -> EmeraldDark
                        "En preparación" -> BrandBlueInteractive
                        else -> TextSecondary
                    }

                    Text(
                        text = gestor.confirmationStatus,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = statusColor
                    )
                }
            }
        }
    }
}

@Composable
fun WeeklyBarChart(performances: List<DayPerformance>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val barCount = performances.size
            val slotWidth = width / barCount
            val barWidth = slotWidth * 0.45f
            val maxAmount = 40000f
            val targetAmount = 26400f
            val chartBottom = height - 26.dp.toPx()
            val chartTop = 20.dp.toPx()
            val availableHeight = chartBottom - chartTop

            // Target dashed line
            val targetY = chartBottom - (targetAmount / maxAmount) * availableHeight
            drawLine(
                color = Color(0xFF94A3B8),
                start = Offset(0f, targetY),
                end = Offset(width, targetY),
                strokeWidth = 1.5.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            )

            // Draw bars
            performances.forEachIndexed { index, perf ->
                val barLeft = index * slotWidth + (slotWidth - barWidth) / 2f
                val barHeight = if (perf.amountAchieved > 0) {
                    (perf.amountAchieved.toFloat() / maxAmount) * availableHeight
                } else {
                    40.dp.toPx() // placeholder light bar
                }
                val barTop = chartBottom - barHeight

                val barColor = when {
                    perf.isTargetMet -> EmeraldTarget
                    perf.amountAchieved > 0 -> BrandBlueInteractive
                    else -> Color(0xFFDAE2FD) // light future bar
                }

                drawRoundRect(
                    color = barColor,
                    topLeft = Offset(barLeft, barTop),
                    size = Size(barWidth, barHeight),
                    cornerRadius = CornerRadius(6.dp.toPx(), 6.dp.toPx())
                )

                // Draw amount above bar if active
                if (perf.amountAchieved > 0) {
                    drawContext.canvas.nativeCanvas.apply {
                        val amountText = "$${(perf.amountAchieved / 1000).toInt()}k"
                        val paint = android.graphics.Paint().apply {
                            color = if (perf.isTargetMet) android.graphics.Color.parseColor("#047857")
                            else android.graphics.Color.parseColor("#2563EB")
                            textSize = 10.sp.toPx()
                            isFakeBoldText = true
                            textAlign = android.graphics.Paint.Align.CENTER
                        }
                        drawText(amountText, barLeft + barWidth / 2f, barTop - 6.dp.toPx(), paint)
                    }
                }

                // Draw day label below
                drawContext.canvas.nativeCanvas.apply {
                    val paint = android.graphics.Paint().apply {
                        color = android.graphics.Color.parseColor("#0F172A")
                        textSize = 11.sp.toPx()
                        textAlign = android.graphics.Paint.Align.CENTER
                    }
                    drawText(perf.dayName, barLeft + barWidth / 2f, chartBottom + 16.dp.toPx(), paint)
                }
            }

            // Draw "Meta diaria" label on right edge
            drawContext.canvas.nativeCanvas.apply {
                val paint = android.graphics.Paint().apply {
                    color = android.graphics.Color.parseColor("#64748B")
                    textSize = 9.sp.toPx()
                    textAlign = android.graphics.Paint.Align.RIGHT
                }
                drawText("Meta diaria", width - 4.dp.toPx(), targetY - 4.dp.toPx(), paint)
            }
        }
    }
}
